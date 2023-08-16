package org.darien.types.utils.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.darien.types.FailureArgIsFalse;
import org.darien.types.FailureArgIsNull;
import org.darien.types.FailureError;
import org.darien.types.FailureException;
import org.darien.types.S;
import org.darien.types.impl.FAIF;
import org.darien.types.impl.FAIN;
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
    	FailureArgIsNull fain_r = FailureUtils.theNull(new Object[] {null, null});
    	assertTrue(fain_r != null);
    	assertTrue(fain_r instanceof FAIN);
    	
    	S obj = TestUtils.getField("org.darien.types.impl.ArgsList", "idxs", fain_r);
    	
    	if(obj.eval()) {
    		List<Number> idxs = (List<Number>) obj.unwrap();

    		assertTrue(idxs.size() == 2);
    		assertTrue((int)idxs.get(0) == 0);
    		assertTrue((int)idxs.get(1) == 1);
    	} else {
            switch (obj) {
                case FailureError fe -> assertTrue(fe.getLocation(), false);
                case FailureException fe -> assertTrue(fe.getLocation(), false);
                case FailureArgIsNull fain -> assertTrue(fain.getLocation(), false);
                default  -> System.out.println("As currently written, not possible.");
            }
    	}
    }
    @Test
    void failure_args_two_nulls_no_switch() {
    	FailureArgIsNull fain_r = FailureUtils.theNull(new Object[] {null, null});
    	assertTrue(fain_r != null);
    	assertTrue(fain_r instanceof FAIN);
    	
    	S obj = TestUtils.getField("org.darien.types.impl.ArgsList", "idxs", fain_r);
    	
    	if(obj.eval()) {
    		List<Number> idxs = (List<Number>) obj.unwrap();

			System.out.println("Success path");
			 
    		assertTrue(idxs.size() == 2);
    		assertTrue((int)idxs.get(0) == 0);
    		assertTrue((int)idxs.get(1) == 1);
    	} else {
    		int i = 3;
    		
    		if(obj instanceof FailureError) {
        		i = 0;
        	} else {
        		if(obj instanceof FailureException) {
            		i = 1;
            	} else {
            		if(obj instanceof FailureArgIsNull) {
                		i = 2;
                	}
            	}
        	}
    		
    		switch(i) {
	    		case 0 : FailureError ferr = (FailureError) obj;
				         System.out.println("case 0");
	    				 assertTrue(ferr.getLocation(), false);
	    		case 1 : FailureException fexp = (FailureException) obj;
	         			 System.out.println("case 1");
	    				 assertTrue(fexp.getLocation(), false);
	    		case 2 : FailureArgIsNull fain = (FailureArgIsNull) obj;
	        			 System.out.println("case 2");
	    				 assertTrue(fain.getLocation(), false);
	    		default : System.out.println("As currently written, not possible.");
	    				  System.out.println("case 3");
    		}
    	}
    }
    
    private int typeToInt_TestUtils_getField(Object obj) {
    	if(obj instanceof FailureError) {
    		return 0;
    	} else {
    		if(obj instanceof FailureException) {
        		return 1;
        	} else {
        		if(obj instanceof FailureArgIsNull) {
            		return 2;
            	} else {
            		return 3;
            	}
        	}
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
                case FailureArgIsNull fain -> assertTrue(fain.getLocation(), false);
                default  -> System.out.println("As currently written, not possible.");
            }
    	}
    }
}