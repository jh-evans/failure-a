package org.darien.tools.codegen;

import java.util.ArrayList;
import java.util.List;

public class CodeNode {
	protected String code;
	protected List<CodeNode> children;

	public CodeNode() {
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
	
	public String toString() {
		String result = code;
		
		for(CodeNode node : this.children) {
			result += node;
		}
		
		return result;
	}
}