package org.darien.tools.codegen;

import java.util.ArrayList;
import java.util.List;

public class CodeNode {
	protected String code;
	protected List<CodeNode> children;

	public final static CodeNode space = new CodeNode(" ");
	public final static CodeNode nl = new CodeNode("\n");
	public final static CodeNode oc = new CodeNode("{");
	public final static CodeNode cc = new CodeNode("}");
	public final static CodeNode op = new CodeNode("(");
	public final static CodeNode cp = new CodeNode(")");

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
	
	public String toString() {
		String result = code;
		
		for(CodeNode node : this.children) {
			result += node;
		}
		
		return result;
	}
}