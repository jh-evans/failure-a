package org.darien.tools.codegen;

import java.util.ArrayList;
import java.util.List;

public class CodeNode {
	public static int cbc;
	protected String code;
	protected List<CodeNode> children;

	public CodeNode() {
		this.cbc = 0;
		this.code = "";
		this.children = new ArrayList<CodeNode>();
	}
	
	public CodeNode(String code) {
		this();
		this.code = code;
	}
	
	public void addChild(CodeNode node) {
		this.children.add(node);
	}
	
	public String getCode() {
		return this.code;
	}
	
	public static void cbc(int cbc) {
		CodeNode.cbc = cbc;
	}
	
    public String spaces() {
    	if(cbc == 0) {
    		return "";
    	}
    	return String.format("%" + cbc + "s", ""); 
    }
	
	public String toString() {
		String result = code;
		
		for(CodeNode node : this.children) {
			result = result + node;
		}
		
		return result;
	}
}