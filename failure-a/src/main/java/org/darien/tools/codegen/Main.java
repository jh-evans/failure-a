package org.darien.tools.codegen;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
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

public class Main {
	public CodeGen generate(String classname, boolean debug_on) {
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
									
									ri = new ReturnInvocation(constantutf8.getBytes(), cp.constantToString(((ConstantCP) c).getNameAndTypeIndex(), Const.CONSTANT_NameAndType));									
						            
									break;
						}
					}
			
					if(debug_on) {
						for(ReturnInvocation ret_i : rets) {
							System.out.println(ret_i);
						}
					}

					CodeGen cg = new CodeGen();
					
					cg.addImport(m.getReturnType().getCanonicalName());
					cg.setSimpleType(m);
					cg.setMethodCall(m);

					CodeNode cn = cg.addSuccessPath();
					cg.openFailurePath();
					
					for(ReturnInvocation reti : rets) {
						cg.addCaseStatement(reti);
					}
					
					cg.addDefaultCase();

					bytes.close();

					((CodeBlock) cn).closeCodeBlock();

					if(debug_on) {
						System.out.println("Code: " + cg);
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
		
		return new CodeGen();
	}
	
	public static void main(String[] args) {
		if(args.length < 1 || args.length > 2) {
			System.out.println("org.darien.tools.codegen.Main [-debug] <Java classname>");
			System.out.println("The classname must be fully qualified with the entire package name including the classname, e.g., java.lang.String");
			System.exit(99);
		}
		
		Main m = new Main();
		
		if(args[0].equals("-debug")) {
			m.generate(args[1], true);
		} else {

			m.generate(args[0], false);
		}
	}
}
