package org.darien.tools.codegen;

/** Represents an else statement for the generated Java source code which is written as:
 * 
 * if(condition) {
 * } else {
 * }
 * 
 * the code block after the else is added as a child using ElseBlock.
 */
public class ElseStatement extends CodeNode {	
	
	/**
	 * Instantiates a new else statement.
	 */
	public ElseStatement() {
		this.children.add(new CodeNode(" "));
		this.children.add(new CodeNode("else"));
		this.children.add(new CodeNode(" "));
	}

	/**
	 * Adds the child.
	 *
	 * @param code the code
	 */
	public void addChild(CodeNode code) {
		this.children.add(code);
	}
}