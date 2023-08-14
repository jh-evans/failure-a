package org.darien.tools.codegen;


public class Switch extends CodeNode {	
	public Switch(String cond) {
		this.children.add(new CodeNode("switch"));
		this.children.add(new CodeNode("("));
		this.children.add(new CodeNode(cond));
		this.children.add(new CodeNode(")"));
		this.children.add(new CodeNode(" "));
	}

	public void addChild(CodeNode code) {
		this.children.add(code);
	}
}