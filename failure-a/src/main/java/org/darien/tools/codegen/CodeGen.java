package org.darien.tools.codegen;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.bcel.Const;
import org.apache.bcel.Repository;
import org.apache.bcel.classfile.Constant;
import org.apache.bcel.classfile.ConstantCP;
import org.apache.bcel.classfile.ConstantClass;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.ConstantUtf8;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.util.ByteSequence;

public class CodeGen {
	public CodeGenerator generate(String classname, Map<String, Boolean> args) {
		boolean debug_on = false;
		boolean outputcode = false;

		if(args.containsKey("debug")) {
			debug_on = args.get("debug");
		}
		
		if(args.containsKey("outputcode")) {
			outputcode = args.get("outputcode");
		}
		
		if(debug_on) {
			System.out.println("Looking for this class: " + classname);
			System.out.println("Searching this classpath");
			for(String dir : System.getProperty("java.class.path").split(System.getProperty("path.separator"))) {
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
						System.out.println("In this method: " + m);
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
						    		System.out.println("  Found return instruction: " + ri);
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
									
									ri = new ReturnInvocation(constantutf8.getBytes(), cp.constantToString(((ConstantCP) c).getNameAndTypeIndex(), Const.CONSTANT_NameAndType));									
						            
									break;
						}
					}
			
					if(debug_on) {
						System.out.println("The unique types that are returned from the above method are:");
						for(ReturnInvocation ret_i : rets) {
							System.out.println("  " + ret_i);
						}
					}

					CodeGenerator cg = new CodeGenerator(args);
					
					cg.addImport(m.getReturnType().getCanonicalName());
					cg.setSimpleType(m);
					cg.setMethodCall(m);

					String varname = cg.addSuccessPath(rets);

					bytes.close();

					if(outputcode) {
						System.out.println();
						System.out.println("Code is:");
						System.out.println();
						System.out.println(cg);
					}
					
					if(args.containsKey("outputimports") && args.get("outputimports")) {
						for(String im : cg.getImports()) {
							System.out.println(im);
						}
					}
					
					return cg;
				}
			}
		} catch(IOException ioe) {
    		ioe.printStackTrace();
    		System.exit(101);
		} catch(SecurityException se) {
    		se.printStackTrace();
    		System.exit(102);
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
    		System.exit(103);
		}
		
		return new CodeGenerator(args);
	}
	
	public static void main(String[] args) {
		if(args.length < 1 || args.length > 2) {
			System.out.println("org.darien.tools.codegen.Main [-debug] <Java classname>");
			System.out.println("The classname must be fully qualified with the entire package name including the classname, e.g., java.lang.String");
			System.exit(99);
		}
		
		CodeGen m = new CodeGen();

		var arguments = new HashMap<String, Boolean>();
		
		for(int i = 0; i < args.length; i++) {
			if(args[i].equals("-debug")) {
				arguments.put("debug", true);
			}
			
			if(args[i].equals("-pre17")) {
				arguments.put("pre17", true);
			}
			
			if(args[i].equals("-typecheckmethod")) {
				arguments.put("typecheckmethod", true);
			}

			}
		

		m.generate(args[1], arguments);
	}
}
