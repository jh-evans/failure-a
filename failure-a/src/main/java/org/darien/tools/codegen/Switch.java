package org.darien.tools.codegen;

import java.util.ArrayList;
import java.util.List;

public class Switch extends CodeNode {	
	public Switch() {
		this.children.add(new CodeNode("switch(obj)"));
		this.children.add(new CodeNode(" "));
	}

	public void addChild(CodeNode code) {
		this.children.add(code);
	}
}