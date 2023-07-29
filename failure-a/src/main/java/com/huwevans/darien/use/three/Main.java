package com.huwevans.darien.use.three;

import org.apache.commons.lang3.tuple.*;

import com.huwevans.darien.*;
import com.huwevans.darien.impl.*;

public class Main {
	
	public Success<ImmutablePair<String, String>> retrieveTwo(boolean fail) {
		if(fail) {			
			ImmutablePair<String, String> pair = new ImmutablePair<String, String>("Hello", "");
			return new FailurePartialResultImpl<ImmutablePair<String, String>>(pair);
		} else {			
			ImmutablePair<String, String> pair = new ImmutablePair<String, String>("Hello", "world");
			return new SuccessImpl<ImmutablePair<String, String>>(pair);
		}
	}

	public static void main(String[] args) {
		Main m = new Main();

		Success<ImmutablePair<String, String>> pair = m.retrieveTwo(true);
		
		if(pair.eval()) {
			System.out.println("Success, result is: " + pair.unwrap());
		} else {
			switch(pair) {
    			case FailurePartialResult<ImmutablePair<String, String>> fpr ->
    			{
    				System.out.println("Failed with fpr " + fpr.getPartialResult());
    			}
	    		default -> System.out.println("As written, cannot happen");
			}
		}
		
		pair = m.retrieveTwo(false);
		
		if(pair.eval()) {
			System.out.println("Success, result is: " + pair.unwrap());
		} else {
			switch(pair) {
    			case FailurePartialResult<ImmutablePair<String, String>> fpr ->
    			    System.out.println("Failed with fpr " + fpr.unwrap());
	    		default -> System.out.println("As written, cannot happen");
			}
		}
	}
}
