package org.darien.tools.codegen;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CodeGenerator {
	private boolean pre17;
	private boolean typecheckmethod;
	
	private CodeNode root;
	private CodeNode current_child;
	
	private String method_name;
	private List<String> case_stmts;

	private List<String> imports;
	
	public CodeGenerator(Map<String, Boolean> args) {
		this.root = new CodeNode();
		this.case_stmts = new ArrayList<String>();
		this.imports = new ArrayList<String>();

		if(args.containsKey("pre17")) {
			pre17 = args.get("pre17");
		}
		
		if(args.containsKey("typecheckmethod")) {
			typecheckmethod = args.get("typecheckmethod");
		}
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
		method_invoke.addChild(new CodeNode("\n"));
		child.addChild(assignment);
		assignment.addChild(method_invoke);
		
		this.current_child.addChild(child);
		this.current_child = method_invoke;
	}
	
	private String simpleType(String typename) {
		return typename.replace("org.darien.types.", "");
	}
	
	public String addSuccessPath(Set<ReturnInvocation> rets) {
		IfElseStatement if_else_statement = new IfElseStatement("obj.eval()");
		current_child.addChild(if_else_statement);

		if_else_statement.getIfBlock().addChild(new CodeNode("Object unwrapped = obj.unwrap();"));
		if_else_statement.getIfBlock().addChild(new CodeNode("\n"));
		
		if(pre17) {
			ReturnInvocation[] r = rets.toArray(new ReturnInvocation[rets.size()]);
			if_else_statement.getElseBlock().addChild(new IntegerInitialisation("i", r.length));	
			
			CodeNode parent = if_else_statement.getElseBlock();
			for(int i = 0; i < r.length - 1; i++) {
				IfElseStatement ies = new IfElseStatement("obj instanceof " + r[i].simpleTypeName());
				ies.getIfBlock().addChild(new IntegerAssignment("i", i));
				parent.addChild(ies);
				parent = ies.getElseBlock();
			}
			
			IfStatement if_statement = new IfStatement("obj instanceof " + r[r.length - 1].simpleTypeName());
			parent.addChild(if_statement);
			if_statement.getIfBlock().addChild(new IntegerAssignment("i", r.length - 1));
	        
			ElseBlock ecb = if_else_statement.getElseBlock();
			
			Switch s = new Switch("i");
			if_else_statement.getElseBlock().addChild(s);
			
			CodeNode ifb = new SwitchBlock();
			s.addChild(ifb);

			ReturnInvocation[] r_case = rets.toArray(new ReturnInvocation[rets.size()]);
			for(int i = 0; i < r_case.length; i++) {
				addIntegerCaseStatement(ifb, i, r_case[i]);
			}
			
			current_child = ifb;

			current_child.addChild(new CodeNode("default : System.out.println(\"You should not see this\");"));
			current_child.addChild(new CodeNode("\n"));
			
			return "i";
		} else {
			Switch s = new Switch("obj");
			if_else_statement.getElseBlock().addChild(s);
			
			CodeNode ifb = new SwitchBlock();
			s.addChild(ifb);
			
			for(ReturnInvocation reti : rets) {
				addTypedCaseStatement(ifb, reti);
			}

			current_child = ifb;

			current_child.addChild(new CodeNode("default ->"));
			current_child.addChild(new CodeNode(" "));
			current_child.addChild(new CodeNode("{System.out.println(\"You should not see this\");}"));
			current_child.addChild(new CodeNode("\n"));
			
            return "obj";
		}
	}

	public void addIntegerCaseStatement(CodeNode parent, int i, ReturnInvocation reti) {
		addImport((!reti.method_return_type.equals("V") ? reti.method_return_type : reti.type));
		String stn = reti.simpleTypeName();
		
		CodeNode case_line = new CodeNode("case " + i + " : " + stn + " " + varFromSimpleType(stn) + " = " + "(" + stn + ") obj;");
		case_line.addChild(new CodeNode("\n"));
		
		parent.addChild(case_line);
	}
	
	public void addTypedCaseStatement(CodeNode parent, ReturnInvocation reti) {
		addImport((!reti.method_return_type.equals("V") ? reti.method_return_type : reti.type));
		String stn = reti.simpleTypeName();
		
		CodeNode case_line = new CodeNode("case " + stn + " " + varFromSimpleType(stn) + " ->");
		case_line.addChild(new CodeNode("\n"));
		
		parent.addChild(case_line);
	}
	
	public void addImport(String type) {
		imports.add("import " + type + ";");
	}
	
	private String varFromSimpleType(String stn) {
		return stn.chars().filter(Character::isUpperCase)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString()
				.toLowerCase();
	}
	
	public List<String> getImports() {
		Collections.sort(this.imports);
		Collections.reverse(this.imports);
		return this.imports;
	}

	public CodeNode getObj(String v, CodeNode node) {
		//System.out.println("<" + node.getCode() + "," + v + ":" + (node.getCode().equals(v) ? " true" : " false"));
		if(node.getCode().equals(v)) {
			return node;
		} else {
			for(CodeNode n : node.children) {
				CodeNode r = getObj(v, n);
				if(r.getCode().equals(v)) {
					return r;
				}
			}
		}
		
		return new CodeNode(v + " not found");
	}
	
	public String toString() {
		return root.toString();
	}
}