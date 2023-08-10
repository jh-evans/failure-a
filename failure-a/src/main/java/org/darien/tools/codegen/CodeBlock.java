package org.darien.tools.codegen;

import java.util.ArrayList;
import java.util.List;

public class CodeBlock extends CodeNode {
	public CodeBlock() {
		this.children.add(new CodeNode("{"));
		this.children.add(new CodeNode("\n"));
	}
	
	public void addChild(CodeNode node) {
		this.children.add(node);
	}
	
	public void closeCodeBlock() {
		this.children.add(new CodeNode("\n"));
		this.children.add(new CodeNode("}"));
	}
}