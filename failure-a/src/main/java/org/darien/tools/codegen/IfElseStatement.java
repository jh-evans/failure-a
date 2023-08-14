package org.darien.tools.codegen;

public class IfElseStatement extends CodeNode {
	IfBlock if_block;
	ElseBlock else_block;
	
	public IfElseStatement(String cond) {
		this.children.add(new CodeNode("if"));
		this.children.add(new CodeNode("("));
		this.children.add(new CodeNode(cond));
		this.children.add(new CodeNode(")"));
		this.children.add(new CodeNode(" "));
		this.if_block = new IfBlock();
		this.children.add(if_block);
		this.children.add(new ElseStatement());
		this.else_block = new ElseBlock();
		this.children.add(this.else_block);
	}
	
	public IfBlock getIfBlock() {
		return this.if_block;
	}
	
	public ElseBlock getElseBlock() {
		return this.else_block;
	}
	
	public void addChild(CodeNode code) {
		this.children.add(code);
	}
}