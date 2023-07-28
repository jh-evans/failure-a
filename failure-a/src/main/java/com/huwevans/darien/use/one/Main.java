package com.huwevans.darien.use.one;

import com.huwevans.darien.*;
import com.huwevans.darien.impl.*;

public class Main {
	
	/* parsing strings is typically a search for information that will either be found or not.
	 * 
	 * This parse method can fail in one of two ways:
	 * 1. input is null. There is no way to find Hello World in a null object
	 * 2. input is non null and Hello World cannot be found in the submitted string
	 */
	
	public Success<Number> parse(String input) {
        if(input == null) {
            return new FailureExceptionImpl<Number>(new IllegalArgumentException("String input cannot be null"));
		}
		
		int idx = input.indexOf("Hello World");
		
		if(idx == -1) {
			return new FailureValueImpl<Number>(idx);
		} else {
			return new SuccessImpl<Number>(idx);
		}		
	}
	
	public static void main(String[] args) {		
		Main m = new Main();

		/* This call succeeds, returning an instance of Success<Number> so eval() will return true
		 */
		
		Success<Number> idx = m.parse("Hello World");
		if(idx.eval()) {
			System.out.println("Success, found strings starts at index: " + idx.unwrap());
		} else {
			switch(idx) {
    			case FailureValue<Number> fv -> System.out.println("Failed with fv " + fv.getValue());
	    		case FailureException<Number> fe -> System.out.println("Failed with fe " + fe.getException());
		    	default -> System.out.println("As written, cannot happen");
			}
		}

		/* This call fails, returning an instance of FailureException<Number> so eval() will return false
		 */
		
		idx = m.parse(null);
		if(idx.eval()) {
			System.out.println(idx.unwrap());
		} else {
			switch(idx) {
		        case FailureException<Number> fe -> System.out.println("Failed with fe " + fe.getException());
			    case FailureValue<Number> fv -> System.out.println("Failed with fv " + fv.getValue());
			    default -> System.out.println("As written, cannot happen");
			}
		}

		/* This call fails, returning an instance of FailureValue<Number> so eval() will return false
		 */
		idx = m.parse("not there");
		if(idx.eval()) {
			System.out.println(idx.unwrap());
		} else {
			switch(idx) {
			    case FailureException<Number> fe -> System.out.println("Failed with fe " + fe.getException());
			    case FailureValue<Number> fv -> System.out.println("Failed with fv " + fv.getValue());
			    default -> System.out.println("As written, cannot happen");
			}
		}
	}
}
