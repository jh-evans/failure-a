package org.darien.tools.codegen;

import java.util.ArrayList;
import java.util.List;

public class CodeNode {
	private static int cbc;
	protected String code;
	protected List<CodeNode> children;
	
	static {
		CodeNode.cbc = 0;
	}
	
	public CodeNode() {
		this.code = "";
		this.children = new ArrayList<CodeNode>();
	}

	public CodeNode(String code) {
		this();
		this.code = code;
	}
	
	public static void addSpaces(int spacesOffset) {
		CodeNode.cbc += spacesOffset;
	}
	
	public void addChild(CodeNode node) {
		this.children.add(node);
	}
	
	public String getCode() {
		return this.code;
	}
	
    public String spaces() {
    	if(CodeNode.cbc == 0) {
    		return "";
    	}
    	return String.format("%" + CodeNode.cbc + "s", ""); 
    }
    
	public String toString() {
		String result = code;
		
		for(CodeNode node : this.children) {
			result = result + node;
		}
		
		return result;
	}
}