package org.darien.tools.codegen;

public class CodeBlock extends CodeNode {
	public CodeBlock() {
	}
	
	public void addChild(CodeNode node) {
		this.children.add(node);
	}
	
	public void closeCodeBlock() {
	}
	
	public String toString() {
		String result = " {\n";

		CodeNode.cbc += 4;
		result += code;
		
		for(CodeNode node : this.children) {
			result = result + spaces() + node;
		}
		
		CodeNode.cbc -= 4;
		result += "\n" + spaces() + "}";
		
		return result;
	}
}