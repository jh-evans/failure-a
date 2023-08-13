package org.darien.tools.codegen;

public class CodeBlock extends CodeNode {
	int numberSpaces;
	
	public CodeBlock() {
		this.numberSpaces = 4;
	}
	
	public void addChild(CodeNode node) {
		this.children.add(node);
	}
	
	public void closeCodeBlock() {
	}
	
	public String toString() {
		String result = " {\n";

		result += code;

		CodeNode.addSpaces(this.numberSpaces);
		for(CodeNode node : this.children) {
			result = result + spaces() + node;
		}
		CodeNode.addSpaces(-this.numberSpaces);
		
		result += "\n" + spaces() + "}";
		
		return result;
	}
}