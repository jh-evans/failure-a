package org.darien.types.use.one;

import org.darien.types.*;
import org.darien.types.impl.*;
import org.darien.types.utils.FailureUtils;

public class Main {
	
	/* parsing strings is typically a search for information that will either be found or not.
	 * 
	 * This parse method can fail in one of two ways:
	 * 1. input is null. There is no way to find Hello World in a null object
	 * 2. input is non null and Hello World cannot be found in the submitted string
	 */
	
	public S parse(String input) {
		if(FailureUtils.oneIsNull(input)) {
			return FailureUtils.theNull(input);
		}
		
		int idx = input.indexOf("Hello World");
		
		if(idx == -1) {
			return new FV(idx);
		} else {
			return new Success(idx);
		}		
	}
	
	public static void main(String[] args) {		
		Main m = new Main();
		
		S idx = m.parse(null);
		
		if(idx.eval()) {
			int i = (int) idx.unwrap();       // Using generics gets rid of dev having to write this, but increases verbosity
			System.out.println("i is: " + i); // as generics not used on failure side so a lot of redundant typing
		} else {
			switch(idx) {
    			case FailureValue fv -> System.out.println("Failed with fv " + fv.getLocation());
	    		case FailureArgIsNull fain -> System.out.println("Failed with fain " + fain.getLocation());
		    	default -> System.out.println("As written, cannot happen");
			}
		}

		/* This call fails, returning an instance of FailureException so eval() will return false
		 
		idx = m.parse(null);
		if(idx.eval()) {
			System.out.println(idx.unwrap());
		} else {
			switch(idx) {
		        case FailureException fe -> System.out.println("Failed with fe " + fe.getException());
			    case FailureValue fv -> System.out.println("Failed with fv " + fv.getValue());
	    		case FailureArgIsNullImpl fain -> System.out.println("Failed with fain " + fain);
			    default -> System.out.println("As written, cannot happen");
			}
		}
		 */
		/* This call fails, returning an instance of FailureValue so eval() will return false
		
		idx = m.parse("not there");
		if(idx.eval()) {
			System.out.println(idx.unwrap());
		} else {
			switch(idx) {
			    case FailureException fe -> System.out.println("Failed with fe " + fe.getException());
			    case FailureValue fv -> System.out.println("Failed with fv " + fv.getValue());
	    		case FailureArgIsNullImpl fain -> System.out.println("Failed with fe " + fain);
			    default -> System.out.println("As written, cannot happen");
			}
		}
		*/
	}
}
