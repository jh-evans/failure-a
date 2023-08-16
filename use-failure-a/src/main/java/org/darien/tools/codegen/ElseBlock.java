package org.darien.tools.codegen;

/** Represent the 'else' statement when generating Java sourcecode
 * 
 */

public class ElseBlock extends CodeNode {
	
	private int numberSpaces;
	
	/**
	 * Instantiates a new else block.
	 */
	public ElseBlock() {
		this.numberSpaces = 4;
	}
	
	/**
	 * Add a child to this node.
	 *
	 * @param node the node
	 */
	public void addChild(CodeNode node) {
		this.children.add(node);
	}
	
	/**
	 * Print this else block, handling indentation, a configurable number of spaces at a time.
	 *
	 * @return the string
	 */
	public String toString() {
		String result = "{\n";

		result += code;

		CodeNode.addSpaces(this.numberSpaces);
		for(CodeNode node : this.children) {
			result = result + spaces() + node;
		}
		CodeNode.addSpaces(-this.numberSpaces);
		
		result += spaces() + "}\n";
		
		return result;
	}
}