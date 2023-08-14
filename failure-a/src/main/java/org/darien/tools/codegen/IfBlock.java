package org.darien.tools.codegen;

public class IfBlock extends CodeNode {
	int numberSpaces;
	
	public IfBlock() {
		this.numberSpaces = 4;
	}
	
	public void addChild(CodeNode node) {
		this.children.add(node);
	}
	
	public String toString() {
		String result = "{\n";

		result += code;

		CodeNode.addSpaces(this.numberSpaces);
		for(CodeNode node : this.children) {
			result = result + spaces() + node;
		}
		CodeNode.addSpaces(-this.numberSpaces);
		
		result += spaces() + "}";
		
		return result;
	}
}