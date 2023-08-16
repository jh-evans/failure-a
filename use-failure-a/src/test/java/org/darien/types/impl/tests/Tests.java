package org.darien.types.impl.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.darien.types.impl.FAIF;
import org.darien.types.impl.FAIN;
import org.darien.types.impl.FExp;
import org.darien.types.impl.FPR;
import org.darien.types.impl.FV;
import org.darien.types.impl.Success;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

public class Tests {
    @Test
    void success_arg_is_false_test() {
    	assertFalse(new FAIF().eval());
    }
    
    @Test
    void failure_creation_test() {
    	try {
    		Class<?> cls = Class.forName("org.darien.types.F");    
    		assertTrue(Modifier.isAbstract(cls.getModifiers()));
    		return;
        } catch(ClassNotFoundException cnfe ) {
        	assertTrue(false);
        	return;
        } catch(IllegalArgumentException iae) {
			assertTrue(false);
        	return;
		}
    }

    @Test
    void success_arg_is_null_test() {
    	assertFalse(new FAIN().eval());
    }
    
    @Test
    void failure_exception_evaluation_test() {
    	assertFalse(new FExp(new Exception("Test exception")).eval());
    }
    
    @Test
    void failure_partial_result_evaluation_test() {
    	assertFalse(new FPR(new Object()).eval());
    }

    @Test
    void failure_value_evaluation_test() {
    	assertFalse(new FV(6).eval());
    }
    
    @Test
    void success_evaluation_test() {
    	assertTrue(new Success("Test string").eval());
    }
}