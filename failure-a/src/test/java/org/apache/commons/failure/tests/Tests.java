package org.apache.commons.failure.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.huwevans.darien.impl.FailureExceptionImpl;
import com.huwevans.darien.impl.FailureValueImpl;
import com.huwevans.darien.impl.SuccessImpl;

import java.lang.reflect.Modifier;

class Tests {
    @Test
    void success_evaluation_test() {
    	assertTrue(new SuccessImpl<String>("Test string").eval());
    }

    @Test
    void failure_creation_test() {
    	try {
    		Class<?> cls = Class.forName("org.apache.commons.failure.Failure");    
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
    	assertFalse(new FailureValueImpl<String>(6).eval());
    }
    @Test
    void failure_exception_evaluation_test() {
    	assertFalse(new FailureExceptionImpl<String>(new Exception("Test exception")).eval());
    }
}