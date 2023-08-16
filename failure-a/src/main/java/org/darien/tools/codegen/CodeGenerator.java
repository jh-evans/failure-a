package org.darien.tools.codegen;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * This class generates Java failure and success path sourcecode that you paste into your code.
 * <p>
 * Using section at <a href="https://darien-project.readthedocs.io/en/latest/using.html">The Darien Project Documentation</a>.
 */

public class CodeGenerator {
	private boolean pre17;
	
	private CodeNode root;
	private CodeNode current_child;
	
	private String method_name;

	private List<String> imports;
	
	/**
	 * Instantiates a new code generator.
	 *
	 * @param args - The program flags passed from {@link org.darien.tools.codegen.Main}
	 */
	public CodeGenerator(Map<String, Boolean> args) {
		this.root = new CodeNode();
		this.imports = new ArrayList<String>();

		if(args.containsKey("pre17")) {
			pre17 = args.get("pre17");
		}
	}

	/**
	 * Gets the Java imports statements that are required to import the code types and classes generated
	 *
	 * @return the list of import statements
	 */
	public List<String> getImports() {
		Collections.sort(this.imports);
		Collections.reverse(this.imports);
		return this.imports;
	}

	/**
	 * Within the tree of nodes, treating node as a root, recursively search for a node whose code equals v
	 * This is used by {@link org.darien.tools.codegen.tests.Tests} to check the generated source code contains
	 * the Java code expected.
	 * 
	 * @param v the code string being searched for
	 * @param node the root of where in the tree to start searching
	 * @return the found CodeNode
	 */
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
	
	/**
	 * To string. Printing this object causes the generated code to be returned.
	 *
	 * @return the string
	 */
	public String toString() {
		return root.toString();
	}
	
	/**
	 * Gets the root.
	 *
	 * @return the root
	 */
	public CodeNode getRoot() {
		return this.root;
	}
	
	/**
	 * Generate the Java sourcecode for the given method (that must return org.darien.types.S).
	 *
	 * @param m the method the Java sourcecode will be generated for
	 * @param rets contain the types that this metthod returns so the sourcecode handles this in the failure path
	 */
	public void process(Method m, Set<ReturnInvocation> rets) {
		addImport(m.getReturnType().getCanonicalName());
		setSimpleType(m);
		setMethodCall(m);

		addSuccessAndFailurePath(rets);
	}
	
	private void setSimpleType(Method m) {
		CodeNode rt = new CodeNode(simpleType(m.getReturnType().getCanonicalName()));
		root.addChild(rt);
		CodeNode child = new CodeNode(" ");
		rt.addChild(child);
		this.current_child = child;
	}

	private void setMethodCall(Method m) {		
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
	
	private void addSuccessAndFailurePath(Set<ReturnInvocation> rets) {
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
		} else {
			Switch s = new Switch("obj");
			if_else_statement.getElseBlock().addChild(s);
			
			CodeNode ifb = new SwitchBlock();
			s.addChild(ifb);
			
			for(ReturnInvocation reti : rets) {
				addTypedCaseStatement(ifb, reti);
			}

			current_child = ifb;

			current_child.addChild(new CodeNode("default -> System.out.println(\"You should not see this\");"));
			current_child.addChild(new CodeNode("\n"));
		}
	}

	private void addIntegerCaseStatement(CodeNode parent, int i, ReturnInvocation reti) {
		addImport((!reti.method_return_type.equals("V") ? reti.method_return_type : reti.type));
		String stn = reti.simpleTypeName();
		
		CodeNode case_line = new CodeNode("case " + i + " : " + stn + " " + varFromSimpleType(stn) + " = " + "(" + stn + ") obj;");
		case_line.addChild(new CodeNode("\n"));
		
		parent.addChild(case_line);
	}
	
	private void addTypedCaseStatement(CodeNode parent, ReturnInvocation reti) {
		addImport((!reti.method_return_type.equals("V") ? reti.method_return_type : reti.type));
		String stn = reti.simpleTypeName();
		
		CodeNode case_line = new CodeNode("case " + stn + " " + varFromSimpleType(stn) + " ->");
		case_line.addChild(new CodeNode("\n"));
		
		parent.addChild(case_line);
	}
	
	private void addImport(String type) {
		imports.add("import " + type + ";");
	}
	
	private String varFromSimpleType(String stn) {
		return stn.chars().filter(Character::isUpperCase)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString()
				.toLowerCase();
	}
}