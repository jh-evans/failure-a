package org.darien.tools.codegen;

public class IntegerInitialisation extends CodeNode {	
	public IntegerInitialisation(String varname, int v) {		
		this.children.add(new CodeNode("int"));
		this.children.add(new CodeNode(" "));
		this.children.add(new CodeNode(varname));
		this.children.add(new CodeNode(" "));
		this.children.add(new CodeNode("="));
		this.children.add(new CodeNode(" "));
		this.children.add(new CodeNode(String.valueOf(v)));
		this.children.add(new CodeNode(";"));
		this.children.add(new CodeNode("\n"));
	}
	
	public void addChild(CodeNode code) {
		this.children.add(code);
	}
}
