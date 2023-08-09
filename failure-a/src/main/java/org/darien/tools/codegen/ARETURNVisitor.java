package org.darien.tools.codegen;

import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.generic.ARETURN;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.EmptyVisitor;

public class ARETURNVisitor extends EmptyVisitor {
	ConstantPoolGen cpg;
	
	public ARETURNVisitor(JavaClass jc) {
		this.cpg = new ConstantPoolGen(jc.getConstantPool());
	}
	
	public void visitARETURN(ARETURN obj) {
		System.out.println("areturn: " + obj.getType(cpg));
	}
}
