package org.darien.types.utils.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.List;

import org.darien.types.FailureArgIsFalse;
import org.darien.types.FailureArgIsNull;
import org.darien.types.FailureError;
import org.darien.types.FailureException;
import org.darien.types.S;
import org.darien.types.impl.FAIF;
import org.darien.types.impl.FAIN;
import org.darien.types.impl.FErr;
import org.darien.types.impl.FExp;
import org.darien.types.utils.FailureUtils;
import org.junit.jupiter.api.Test;
public class Tests {
    @Test
    void failure_utils_oneisnull_args_is_null_test() {
    	assertTrue(FailureUtils.oneIsNull((Object[]) null));
    }
    
    @Test
    void failure_utils_an_arg_is_null_test() {
    	assertTrue(FailureUtils.oneIsNull(new Object[] {"", null, ""}));
    }
    
    @Test
    void failure_utils_oneisfalse_args_is_null_test() {
    	assertFalse(FailureUtils.oneIsFalse((Boolean[]) null));
    }
    
    @Test
    void failure_utils_an_arg_is_false_test() {
    	assertTrue(FailureUtils.oneIsFalse(new Boolean[] {true, false, true}));
    }

    @Test
    void failure_args_no_args_test() {
    	FailureArgIsNull fain = FailureUtils.theNull();
    	assertTrue(fain != null);
    	assertTrue(fain instanceof FAIN);
    }
    
    @Test
    void failure_args_is_null_result_test() {
    	FailureArgIsNull fain = FailureUtils.theNull((Object[]) null);
    	assertTrue(fain != null);
    	assertTrue(fain instanceof FAIN);
    }

    @Test
    void failure_args_two_nulls_result_test() {
    	FailureArgIsNull fain = FailureUtils.theNull(new Object[] {null, null});
    	assertTrue(fain != null);
    	assertTrue(fain instanceof FAIN);
    	
    	try {
    		Class<?> fain_class = Class.forName("org.darien.types.impl.ArgsList");
    		Field fain_field = fain_class.getDeclaredField("idxs");
    		fain_field.setAccessible(true);
    		List<Number> idxs = (List<Number>) fain_field.get(fain); // bytecode is only checking cast to List, type erasure
    		assertTrue(idxs.size() == 2);
    		assertTrue((int)idxs.get(0) == 0);
    		assertTrue((int)idxs.get(1) == 1);
    	} catch(ClassCastException cce) {
    		assertTrue(false);
    		return;
    	} catch(ClassNotFoundException cnfe) {
    		assertTrue(false);
    		return;
    	} catch (NoSuchFieldException e) {
    		assertTrue(false);
    		return;
		} catch (SecurityException e) {
    		assertTrue(false);
    		return;
		} catch (IllegalArgumentException e) {
    		assertTrue(false);
    		return;
		} catch (ExceptionInInitializerError eiie) {
    		assertTrue(false);
    		return;
		} catch (NullPointerException npe) {
    		assertTrue(false);
    		return;
		} catch (IllegalAccessException e) {
    		assertTrue(false);
    		return;
		}
    }

    @Test
    void failure_args_two_false_result_test() {    	
    	FailureArgIsFalse faif = FailureUtils.theFalse(new Boolean[] {false, false});
    	assertTrue(faif != null);
    	assertTrue(faif instanceof FAIF);
    	
    	S obj = TestUtils.getField("org.darien.types.impl.ArgsList", "idxs", faif);
    	
    	if(obj.eval()) {
    		List<Number> idxs = (List<Number>) obj.unwrap();

    		assertTrue(idxs.size() == 2);
    		assertTrue((int)idxs.get(0) == 0);
    		assertTrue((int)idxs.get(1) == 1);
    	} else {
            switch (obj) {
                case FailureError err -> assertTrue(err.getLocation(), false);
                case FailureException exp -> assertTrue(exp.getLocation(), false);
                default  -> System.out.println("As currently written, not possible.");
            }
    	}
    }
}