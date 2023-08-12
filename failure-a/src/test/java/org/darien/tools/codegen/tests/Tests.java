package org.darien.tools.codegen.tests;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.darien.tools.codegen.CodeGen;
import org.darien.tools.codegen.CodeNode;
import org.darien.tools.codegen.Main;

public class Tests {
	
	CodeNode find(String s) {
    	Main m = new Main();
    	CodeGen cg = m.generate("org.darien.tools.codegen.tests.TestCodeGen", true);

    	return cg.getObj(s, cg.getRoot());
	}

    @Test
    void find_s() {
    	String S = "S";
    	assertTrue(find(S).getCode().equals(S));
    }
	
    @Test
    void find_obj() {
    	String obj = "obj";
    	assertTrue(find(obj).getCode().equals(obj));
    }

    @Test
    void find_obj_eval() {
    	String obj_eval = "obj.eval()";
    	assertTrue(find(obj_eval).getCode().equals(obj_eval));
    }

    @Test
    void find_obj_unwrap() {
    	String obj_unwrap = "    Object unwrapped = obj.unwrap();";
    	assertTrue(find(obj_unwrap).getCode().equals(obj_unwrap));
    }

    @Test
    void find_else_unwrap() {
    	String lse = "else";
    	assertTrue(find(lse).getCode().equals(lse));
    }

    @Test
    void find_switch_unwrap() {
    	String witch = "switch(obj)";
    	assertTrue(find(witch).getCode().equals(witch));
    }

    @Test
    void find_case_ferr() {
    	String ferr = "        case FErr fe ->";
    	assertTrue(find(ferr).getCode().equals(ferr));
    }

    @Test
    void find_case_fexp() {
    	String fexp = "        case FExp fe ->";
    	assertTrue(find(fexp).getCode().equals(fexp));
    }
    
    @Test
    void find_case_fain() {
    	String fain = "        case FailureArgIsNull fain ->";
    	assertTrue(find(fain).getCode().equals(fain));
    }
    
    @Test
    void find_default_case() {
    	String def = "        default ->";
    	assertTrue(find(def).getCode().equals(def));
    }
}