package org.darien.tools.codegen;

import java.util.ArrayList;
import java.util.List;

/**
 * The node in the tree of nodes that holds code when generating the Java source.
 */

public class CodeNode {
	private static int cbc;
	
	/**
	 * Code holds the code for this node in the tree
	 */
	protected String code;
	
	/**
	 * The list of children associated with this node
	 * e.g., if(obj.eval()) {
	 *       } else {
	 *       }
	 *       
	 * is a CodeNode (IfElseStatement) that has eight children, representing the source components: 'if', '(', the boolean condition,
	 * ')', ' ', the if code block, the else statement, and an else code block
	 * 
	 */
	protected List<CodeNode> children;
	
	static {
		CodeNode.cbc = 0;
	}

	/**
	 * Instantiates a new code node.
	 */
	public CodeNode() {
		this.code = "";
		this.children = new ArrayList<CodeNode>();
	}
	
	/**
	 * Instantiates a new code node.
	 *
	 * @param code the code
	 */
	public CodeNode(String code) {
		this();
		this.code = code;
	}
	
	/**
	 * Adds the spaces.
	 *
	 * @param spacesOffset the spaces offset
	 */
	public static void addSpaces(int spacesOffset) {
		CodeNode.cbc += spacesOffset;
	}
	
	/**
	 * Adds the child.
	 *
	 * @param node the node
	 */
	public void addChild(CodeNode node) {
		this.children.add(node);
	}
	
	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return this.code;
	}
	
    /**
     * Spaces.
     *
     * @return the string
     */
    public String spaces() {
    	if(CodeNode.cbc == 0) {
    		return "";
    	}
    	return String.format("%" + CodeNode.cbc + "s", ""); 
    }
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		String result = code;
		
		for(CodeNode node : this.children) {
			result = result + node;
		}
		
		return result;
	}
}