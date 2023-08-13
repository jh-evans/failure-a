package org.darien.tools.codegen;


public class Switch extends CodeNode {	
	public Switch() {
		this.children.add(new CodeNode("switch(obj)"));
	}

	public void addChild(CodeNode code) {
		this.children.add(code);
	}
}