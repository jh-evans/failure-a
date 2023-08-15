package org.darien.tools.codegen.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.darien.tools.codegen.CodeGenerator;
import org.darien.tools.codegen.CodeNode;
import org.darien.tools.codegen.CodeGen;
import org.darien.types.S;
import org.darien.types.FailureArgIsNull;
import org.darien.types.impl.FAIF;
import org.darien.types.impl.FErr;
import org.darien.types.impl.FExp;

public class Tests {
	private CodeGenerator cg;
	
	CodeNode find(String s) {
		if(this.cg == null) {
			CodeGen codegen = new CodeGen();
			var args = new HashMap<String, Boolean>();
			args.put("pre17", true);
			args.put("outputcode", true);
			args.put("outputimports", true);
			this.cg = codegen.generate("org.darien.tools.codegen.tests.TestCodeGen", args);
		}

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
    	String obj_unwrap = "Object unwrapped = obj.unwrap();";
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
    	String ferr = "case FErr fe ->";
    	assertTrue(find(ferr).getCode().equals(ferr));
    }

    @Test
    void find_case_fexp() {
    	String fexp = "case FExp fe ->";
    	assertTrue(find(fexp).getCode().equals(fexp));
    }
    
    @Test
    void find_case_fain() {
    	String fain = "case FailureArgIsNull fain ->";
    	assertTrue(find(fain).getCode().equals(fain));
    }
    
    @Test
    void find_default_case() {
    	String def = "default -> {}";
    	assertTrue(find(def).getCode().equals(def));
    }
    
    @Test
    void call_getField_success() {
    	FAIF faif = new FAIF();
    	S obj = TestCodeGen.getField("org.darien.types.impl.ArgsList", "idxs", faif);
    	
    	if(obj.eval()) {
    	    Object unwrapped = obj.unwrap();
    	    assertTrue(unwrapped instanceof Object);
    	} else {
    	    switch(obj) {
    	        case FailureArgIsNull fain -> assertTrue(fain.getLocation(), false);
    	        case FExp fe -> assertTrue(fe.getLocation(), false);
    	        case FErr fe -> assertTrue(fe.getLocation(), false);
    	        default -> assertTrue(false);
    	    }
    	}
    }
    
    @Test
    void call_getField_classname_wrong() {
    	FAIF faif = new FAIF();
    	String cn = "org.darien.types.impl.ArgsLis";
    	S obj = TestCodeGen.getField(cn, "idxs", faif);
    	
    	if(obj.eval()) {
    	    Object unwrapped = obj.unwrap();
    	    assertTrue(unwrapped instanceof Object);
    	} else {
    	    switch(obj) {
    	        case FailureArgIsNull fain -> assertTrue(fain.getLocation(), false);
    	        case FExp fe -> assertTrue(fe.getException().getMessage().equals(cn));
    	        case FErr fe -> assertTrue(fe.getLocation(), false);
    	        default -> assertTrue(false);
    	    }
    	}
    }
    
    @Test
    void call_getField_fieldname_wrong() {
    	FAIF faif = new FAIF();
    	String mn = "idx";
    	S obj = TestCodeGen.getField("org.darien.types.impl.ArgsList", mn, faif);
    	
    	if(obj.eval()) {
    	    Object unwrapped = obj.unwrap();
    	    assertTrue(unwrapped instanceof Object);
    	} else {
    	    switch(obj) {
    	        case FailureArgIsNull fain -> assertTrue(fain.getLocation(), false);
    	        case FExp fe -> assertTrue(fe.getException().getMessage().equals(mn));
    	        case FErr fe -> assertTrue(fe.getLocation(), false);
    	        default -> assertTrue(false);
    	    }
    	}
    }
    
    @Test
    void call_getField_instance_wrong() {
    	String mn = "idx";
    	S obj = TestCodeGen.getField("org.darien.types.impl.ArgsList", mn, null);
    	
    	if(obj.eval()) {
    	    Object unwrapped = obj.unwrap();
    	    assertTrue(unwrapped instanceof Object);
    	} else {
    	    switch(obj) {
    	        case FailureArgIsNull fain -> assertEquals(fain.getIndices().get(0), 2);
    	        case FExp fe -> assertTrue(fe.getLocation(), false);
    	        case FErr fe -> assertTrue(fe.getLocation(), false);
    	        default -> assertTrue(false);
    	    }
    	}
    }
}