package org.darien.tools.codegen;

import java.util.ArrayList;
import java.util.List;

public class IfStatement extends CodeNode {	
	public IfStatement(String cond) {		
		this.children.add(new CodeNode("\n"));
		this.children.add(new CodeNode("if"));
		this.children.add(new CodeNode("("));
		this.children.add(new CodeNode(cond));
		this.children.add(new CodeNode(")"));
	}
	
	public void addChild(CodeNode code) {
		this.children.add(code);
	}
}