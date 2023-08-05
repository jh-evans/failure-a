package org.darien.types.use.notes;

import org.darien.types.*;
import org.darien.types.impl.*;

public class Main {
	
	/* parsing strings is typically a search for information that will either be found or not.
	 * 
	 * This parse method can fail in one of two ways:
	 * 1. input is null. There is no way to find Hello World in a null object
	 * 2. input is non null and Hello World cannot be found in the submitted string
	 */
	
	public S parse(String input) {
        if(input == null) {
            return new FE(new IllegalArgumentException("String input cannot be null"));
		}
		
		int idx = input.indexOf("Hello World");
		
		if(idx == -1) {
			return new FV(idx);
		} else {
			return new Success(idx);
		}		
	}

	private String rhs(String input) {
		return input.split("-")[1];
	}

	/* Code below to be provided by IDE if above method is annotated
	 */
	
	private S rhs_checked(String input) {
        if(input == null) {
            return new FE(new IllegalArgumentException("String input cannot be null"));
        }
        
		try {
			return new Success(rhs(input));
		} catch(ArrayIndexOutOfBoundsException oobe) {
			return new FE(oobe);
		} catch(NullPointerException npe) {
			return new FE(npe);
		}
	}

	public static void main(String[] args) {		
		/*
		 if(rhs.eval() -> String right_hand_side) { //assignment to right_hand_side from unwrap implicit here for true block
			System.out.println("right hand side " + right_hand_side);
		} else {
		    // implicit cast IF can determine can only be one kind of failure passed back
		    FailureException<String> fe -> System.out.println("Failed with fe " + fe.getException());
		    }
		}
		*/
	}
}
