package org.darien.tools.codegen;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CodeGen {
	private CodeNode root;
	private CodeNode current_child;
	
	private String return_type;
	private String method_name;
	private String if_statement;
	private String success_block;
	private String else_statement;
	private String failure_block;
	private List<String> case_stmts;
	private String close_case;
	private String close_failure_block;

	private List<String> imports;
	
	public CodeGen() {
		this.root = new CodeNode();
		this.case_stmts = new ArrayList<String>();
		this.imports = new ArrayList<String>();
	}
	
	public CodeNode getRoot() {
		return this.root;
	}
	
	public void setSimpleType(Method m) {
		CodeNode rt = new CodeNode(simpleType(m.getReturnType().getCanonicalName()));
		root.addChild(rt);
		CodeNode child = new CodeNode(" ");
		rt.addChild(child);
		this.current_child = child;
		
		this.return_type = simpleType(m.getReturnType().getCanonicalName());
	}

	
	public void setMethodCall(Method m) {		
		if(Modifier.isStatic(m.getModifiers())) {
			String name =  m.getDeclaringClass().getCanonicalName();
			String[] name_components = name.split("\\.");
			this.method_name = name_components[name_components.length - 1] + "." + m.getName();
		} else {
			this.method_name = m.getName();
		}
		
		CodeNode child = new CodeNode("obj");
		child.addChild(new CodeNode(" "));
		CodeNode assignment = new CodeNode("=");
		assignment.addChild(new CodeNode(" "));
		CodeNode method_invoke = new CodeNode(this.method_name);
		method_invoke.addChild(new CodeNode("();"));
		child.addChild(assignment);
		assignment.addChild(method_invoke);
		
		this.current_child.addChild(child);
		this.current_child = method_invoke;
	}
	
	private String simpleType(String typename) {
		return typename.replace("org.darien.types.", "");
	}
	
	public CodeNode addSuccessPath() {
		this.if_statement = "if(obj.eval()) {" + "\n";
		this.success_block = "    Object unwrapped = obj.unwrap();" + "\n";
		this.else_statement = "} else {" + "\n";
		
		IfStatement if_statement = new IfStatement("obj.eval()");
		current_child.addChild(if_statement);
		
		CodeBlock icb = new CodeBlock();
		icb.addChild(new CodeNode("    Object unwrapped = obj.unwrap();"));
		icb.closeCodeBlock();
		if_statement.addChild(icb);
		
		ElseStatement es = new ElseStatement();

		CodeBlock ecb = new CodeBlock();
		es.addChild(ecb);
		
        if_statement.addChild(es);
		
		current_child = ecb;
		
		return current_child;
	}
	
	public void openFailurePath() {
		Switch s = new Switch();
		current_child.addChild(s);
		
		CodeBlock ecb = new CodeBlock();
		s.addChild(ecb);
		
		current_child = ecb;
		
		this.failure_block = "    switch(obj) {" + "\n";
	}
	
	public void addCaseStatement(ReturnInvocation reti) {
		addImport((!reti.method_return_type.equals("V") ? reti.method_return_type : reti.type));
		String stn = reti.simpleTypeName();
		this.case_stmts.add("        case " + stn + " " + varFromSimpleType(stn) + " ->" + "\n");
		
		CodeNode case_line = new CodeNode("        case " + stn + " " + varFromSimpleType(stn) + " ->");
		case_line.addChild(new CodeNode("\n"));
		
		current_child.addChild(case_line);
	}
	
	public void addImport(String type) {
		imports.add("import " + type);
	}
	
	private String varFromSimpleType(String stn) {
		return stn.chars().filter(Character::isUpperCase)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString()
				.toLowerCase();
	}
	
	public void addDefaultCase() {
		this.case_stmts.add("        default ->" + "\n");
		
		// in addCaseStatement, current_child is a CodeBlcok so this works, but not a good idea
		current_child.addChild(new CodeNode("        default ->"));
		((CodeBlock) current_child).closeCodeBlock();
	}
	
	public void closeCase() {
		this.close_case = "    }" + "\n";
	}
	
	public void closeFailurePath() {
		this.close_failure_block = "}";
	}
	
	public List<String> getImports() {		
		Collections.sort(this.imports);
		Collections.reverse(this.imports);
		return this.imports;
	}
	
	public String toString() {		
		String code = return_type + " " + method_name + "\n";
		code += this.if_statement;
		code += this.success_block;
		code += this.else_statement;
		code += this.failure_block;
		
		for(String s : this.case_stmts) {
			code += s;
		}
		
		code += this.close_case;
		code += this.close_failure_block;
		
		return code;
	}
}