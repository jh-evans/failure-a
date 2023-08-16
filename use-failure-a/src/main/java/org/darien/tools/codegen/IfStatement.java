package org.darien.tools.codegen;

public class IfStatement extends CodeNode {
	IfBlock if_block;
	IfBlock else_block;
	
	public IfStatement(String cond) {
		this.children.add(new CodeNode("if"));
		this.children.add(new CodeNode("("));
		this.children.add(new CodeNode(cond));
		this.children.add(new CodeNode(")"));
		this.children.add(new CodeNode(" "));
		this.if_block = new IfBlock();
		this.children.add(if_block);
		this.children.add(new CodeNode("\n"));
	}
	
	public IfBlock getIfBlock() {
		return this.if_block;
	}
	
	public void addChild(CodeNode code) {
		this.children.add(code);
	}
}