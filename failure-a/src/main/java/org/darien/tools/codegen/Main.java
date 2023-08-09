package org.darien.tools.codegen;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.bcel.Const;
import org.apache.bcel.Repository;
import org.apache.bcel.classfile.Constant;
import org.apache.bcel.classfile.ConstantCP;
import org.apache.bcel.classfile.ConstantClass;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.ConstantUtf8;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.util.ByteSequence;

public class Main {	
	public static void main(String[] args) {
		if(args.length < 1 || args.length > 2) {
			System.out.println("org.darien.tools.codegen.Main [-debug] <Java classname>");
			System.out.println("The classname must be fully qualified with the entire package name including the classname, e.g., java.lang.String");
			System.exit(99);
		}
		
		boolean debug_on = false;
		String classname = null;
		
		if(args[0].equals("-debug")) {
			debug_on = true;
			classname = args[1];
		} else {
			classname = args[0];
		}

		if(debug_on) {
			System.out.println("Looking for this class: " + classname);
			System.out.println("Searching this classpath");
			for(String dir : System.getProperty("java.class.path").split(";")) {
				System.out.println("  " + dir);
			}
		}
		
		CodeGenClassLoader ldr = new CodeGenClassLoader();

		Class<?> cls = null;
		
		try {
			cls = ldr.loadClass(classname, true);
		}
		catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			System.exit(100);
		}
		
		try {
			Method[] dmethods = cls.getDeclaredMethods();
			for(Method m : dmethods) {
				if(m.getReturnType().getName().equals("org.darien.types.S")) {
					if(debug_on) {
						System.out.println("Found method: " + m);
					}
					
					JavaClass jc = Repository.lookupClass(cls);
					org.apache.bcel.classfile.Method bcel_m = jc.getMethod(m);
					ConstantPool cp = bcel_m.getConstantPool();
					ByteSequence bytes = new ByteSequence(bcel_m.getCode().getCode());
					ReturnInvocation ri = null;
					Set<ReturnInvocation> rets = new HashSet<ReturnInvocation>();
					
					while(bytes.available() > 0) {
						short opcode = (short) bytes.readUnsignedByte();
						
						switch(opcode) {
						    case Const.ARETURN:
						    	if(debug_on) {
						    		System.out.println("Found return instruction: " + ri);
						    	}
						    	if(!ri.isSuccessType(org.darien.types.impl.Success.class)) {
						    		rets.add(ri);
						    	}
						    	break;
							case Const.INVOKESPECIAL:
							case Const.INVOKESTATIC:
									int index = bytes.readUnsignedShort();
									Constant c = cp.getConstant(index);
									Constant cmf = cp.getConstant(((ConstantCP) c).getClassIndex(), Const.CONSTANT_Class);
									int idx = ((ConstantClass) cmf).getNameIndex();
									ConstantUtf8 constantutf8 = cp.getConstantUtf8(idx);
									
									ri = new Main().new ReturnInvocation(constantutf8.getBytes(), cp.constantToString(((ConstantCP) c).getNameAndTypeIndex(), Const.CONSTANT_NameAndType));									
						            
									break;
						}
					}
			
					if(debug_on) {
						for(ReturnInvocation ret_i : rets) {
							System.out.println(ret_i);
						}
					}
					
					Set<String> imports = new HashSet<String>();
					imports.add("import " + m.getReturnType().getCanonicalName());
					
					String code = "";
					code += simpleType(m.getReturnType().getCanonicalName()) + " obj = " + getMethodCall(m) + "();" + "\n";
					code += "\n";
					code += "if(obj.eval()) {" + "\n";
					code += "    " + "Object unwrapped = obj.unwrap();" + "\n";
					code += "} else {" + "\n";
					code += "    switch(obj) {" + "\n";
					
					for(ReturnInvocation reti : rets) {
						imports.add("import " + (!reti.method_return_type.equals("V") ? reti.method_return_type : reti.type));
						String stn = reti.simpleTypeName();
						code += "        case " + stn + " " + varFromSimpleType(stn) + " ->" + "\n";
					}
					
					code += "        default ->" + "\n";
					
					code += "    }" + "\n";
					code += "}";
					
					System.out.println(code);
				
					List<String> i = new ArrayList<String>(imports);
					
					Collections.sort(i);
					Collections.reverse(i);
					
					for(String s : i) {
						System.out.println(s);
					}
					/*
    	S obj = TestCodeGen.getField("org.darien.types.impl.ArgsList", "idxs", fain);
    	
    	if(obj.eval()) {
    		List<Number> idxs = (List<Number>) obj.unwrap();

    		assertTrue(idxs.size() == 2);
    		assertTrue((int)idxs.get(0) == 0);
    		assertTrue((int)idxs.get(1) == 1);
    	} else {
            switch (obj) {
                case FailureError err -> assertTrue(err.getLocation(), false);
                case FailureException exp -> assertTrue(exp.getLocation(), false);
                case FailureArgIsNull fargin -> assertTrue(fargin.getLocation(), false);
                default  -> System.out.println("As currently written, not possible.");
            }
    	}
					 */
				}
			}
		} catch(IOException ioe) {
    		ioe.printStackTrace();
		} catch(SecurityException se) {
    		se.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
	
	private static String varFromSimpleType(String stn) {
		return stn.chars().filter(Character::isUpperCase).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString().toLowerCase();
	}
	
	private static String getMethodCall(Method m) {		
		if(Modifier.isStatic(m.getModifiers())) {
			String name =  m.getDeclaringClass().getCanonicalName();
			String[] name_components = name.split("\\.");
			return name_components[name_components.length - 1] + "." + m.getName();
		} else {
			return m.getName();
		}
	}
	
	private static String simpleType(String typename) {
		return typename.replace("org.darien.types.", "");
	}
	
	private class ReturnInvocation {
		public final String type;
		public final String method_name;
		public final String method_args;
		public final String method_return_type;
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Objects.hash(this.simpleTypeName());

			return result;
		}

		@Override
		public boolean equals(Object obj) {			
			if (this == obj) {
				return true;
			}
			if (!(obj instanceof ReturnInvocation)) {
				return false;
			}
			
			ReturnInvocation other = (ReturnInvocation) obj;
			
			return Objects.equals(this.simpleTypeName(), other.simpleTypeName());
		}
				
		public ReturnInvocation(String type, String method_signature) {
			this.type = type.replace('/', '.');
			String[] m_sig = method_signature.split(" ");
			this.method_name = m_sig[0];
			String[] m_args = m_sig[1].split("\\)");
			this.method_args = m_args[0] + ")";
			
			if (m_args[1].charAt(0) == 'L') {
				this.method_return_type = m_args[1].substring(1).replace('/', '.');
			} else {
				this.method_return_type = m_args[1];
			}
		}
		
		public String simpleTypeName() {
			if(method_return_type.charAt(0) != 'V') {
				if(method_return_type.contains(".")) {
					String[] components = method_return_type.split("\\.");
					String str = components[components.length - 1];
					return str.substring(0, str.length() - 1);
				} else {
					return type;
				}
			} else {
				if(type.contains(".")) {
					String[] components = type.split("\\.");
					return components[components.length - 1];
				} else {
					return type;
				}
			}
		}
		
		public boolean isSuccessType(Class<?> s_cls) {
			return s_cls.getName().equals(this.type);
		}
		
		public String toString() {
			return type + " " + method_name + " " + method_args + " " + method_return_type;
		}
	}
}
