package com.huwevans.darien.use.one;

import com.huwevans.darien.*;
import com.huwevans.darien.impl.*;

public class Main {
	
	/* If Failure is defined thus to 'switch off' generics on the failure side 
	 * 
		public interface Failure extends Success<Object> {
		}
		
		then we cannot do this
		
		public Success<Number> parse(String input) {
        if(input == null) {
            return new FailureExceptionImpl(new IllegalArgumentException("String input cannot be null"));
		}
		
		Line 17 above will not compile as it is FailureExceptionImpl from Success<Object> which is more general than
		the Number used in signature for Success<Number>.
		
		Therefore, we have to have T everywhere or nowhere.
		
		Success<Number> give us success.unwrap without the engineer having to write a cast. The cast is there, it is
		provided by the compiler so at run-time the cast must be performed.
	 */
	
	public Success<Number> parse(String input) {
        if(input == null) {
            return new FailureExceptionImpl(new IllegalArgumentException("String input cannot be null"));
		}
		
		int idx = input.indexOf("Hello World");
		
		if(idx == -1) {
			return new FailureValueImpl(idx);
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
    			case FailureValue fv -> System.out.println("Failed with fv " + fv.getValue());
	    		case FailureException fe -> System.out.println("Failed with fe " + fe.getException());
		    	default -> System.out.println("As written, cannot happen");
			}
		}
	}
}
