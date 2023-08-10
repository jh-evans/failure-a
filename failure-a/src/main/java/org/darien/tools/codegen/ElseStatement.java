package org.darien.tools.codegen;

import java.util.ArrayList;
import java.util.List;

public class ElseStatement extends CodeNode {	
	public ElseStatement() {
		this.children.add(new CodeNode(" "));//CodeNode.space);
		this.children.add(new CodeNode("else"));
		this.children.add(new CodeNode(" "));
	}

	public void addChild(CodeNode code) {
		this.children.add(code);
	}
}