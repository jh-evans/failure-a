package org.darien.tools.codegen;

import java.io.IOException;
import java.lang.reflect.Method;
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
		boolean verbose = false;
		boolean outputcode = false;

		if(args.containsKey("v")) {
			verbose = args.get("v");
		}
		
		if(args.containsKey("outputcode")) {
			outputcode = args.get("outputcode");
		}
		
		if(verbose) {
			System.out.println("Looking for this class: " + classname);
			String[] classpath_locations = System.getProperty("java.class.path").split(System.getProperty("path.separator"));
			System.out.println("Searching <" + classpath_locations.length + "> classpath locations");
			for(String dir : classpath_locations) {
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
					if(verbose) {
						System.out.println("In this method <" + m.getName() + ">:");
						System.out.println("  " + m.toGenericString());
					}
					
					JavaClass jc = Repository.lookupClass(cls);
					org.apache.bcel.classfile.Method bcel_m = jc.getMethod(m);
					ConstantPool cp = bcel_m.getConstantPool();
					ByteSequence bytes = new ByteSequence(bcel_m.getCode().getCode());
					ReturnInvocation ri = null;
					Set<ReturnInvocation> rets = new HashSet<ReturnInvocation>();
					
					String msg = "Return invocations are:\n";
					
					while(bytes.available() > 0) {
						short opcode = (short) bytes.readUnsignedByte();
						
						switch(opcode) {
						    case Const.ARETURN:
						    	if(verbose) {
						    		msg += "  " + ri + "\n";
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
			
					if(verbose) {
						if(!msg.equals("Return invocations are:")) {
							System.out.println(msg.stripTrailing());
						}
						System.out.println("For method <" + m.getName() + "> these are the unique types returned:");
						for(ReturnInvocation ret_i : rets) {
							System.out.println("  " + ret_i);
						}
					}

					CodeGenerator cg = new CodeGenerator(args);
					cg.process(m ,  rets);

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
}
