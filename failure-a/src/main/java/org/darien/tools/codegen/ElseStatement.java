package org.darien.tools.codegen;

public class ElseStatement extends CodeNode {	
	
	public ElseStatement() {
		this.children.add(new CodeNode(" "));
		this.children.add(new CodeNode("else"));
		this.children.add(new CodeNode(" "));
	}

	public void addChild(CodeNode code) {
		this.children.add(code);
	}
}