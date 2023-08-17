package org.darien.use.three;

import org.apache.commons.lang3.tuple.*;
import org.darien.types.*;
import org.darien.types.impl.*;

public class Main {
	
	public S retrieveTwo(boolean fail) {
		if(fail) {			
			ImmutablePair<String, String> pair = new ImmutablePair<String, String>("Hello", "");
			return new FPR(pair);
		} else {			
			ImmutablePair<String, String> pair = new ImmutablePair<String, String>("Hello", "world");
			return new Success(pair);
		}
	}

	public static void main(String[] args) {
		Main m = new Main();

		S pair = m.retrieveTwo(true);
		
		if(pair.eval()) {
			System.out.println("S, result is: " + pair.unwrap());
		} else {
			switch(pair) {
    			case FPR fpr ->
    			{
    				System.out.println("Failed with fpr: " + fpr.getPartialResult());
    				System.out.println("Failure location: " + fpr.getLocation());
    			}
	    		default -> System.out.println("As written, cannot happen");
			}
		}
		
		pair = m.retrieveTwo(false);
		
		if(pair.eval()) {
			System.out.println("S, result is: " + pair.unwrap());
		} else {
			switch(pair) {
    			case FPR fpr ->
    			    System.out.println("Failed with fpr " + fpr.unwrap());
	    		default -> System.out.println("As written, cannot happen");
			}
		}
	}
}
