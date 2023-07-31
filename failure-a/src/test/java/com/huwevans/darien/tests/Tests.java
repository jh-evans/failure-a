package com.huwevans.darien.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.huwevans.darien.types.impl.FE;
import com.huwevans.darien.types.impl.FV;
import com.huwevans.darien.types.impl.Success;

import java.lang.reflect.Modifier;

class Tests {
    @Test
    void success_evaluation_test() {
    	assertTrue(new Success("Test string").eval());
    }

    @Test
    void failure_creation_test() {
    	try {
    		Class<?> cls = Class.forName("com.huwevans.darien.types.F");    
    		assertTrue(Modifier.isAbstract(cls.getModifiers()));
    		return;
        } catch(ClassNotFoundException cnfe ) {
        	assertTrue(false);
        } catch(IllegalArgumentException iae) {
			assertTrue(false);
		}

    	assertTrue(false);
    }

    @Test
    void failure_value_evaluation_test() {
    	assertFalse(new FV(6).eval());
    }
    @Test
    void failure_exception_evaluation_test() {
    	assertFalse(new FE(new Exception("Test exception")).eval());
    }
}