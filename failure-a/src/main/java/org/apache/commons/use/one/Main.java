package org.apache.commons.use.one;

import org.apache.commons.failure.*;
import org.apache.commons.failure.impl.*;

public class Main {
	
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

		Success<Number> idx = m.parse("Hello World");
		if(idx.eval()) {
			System.out.println("Success, result is: " + idx.unwrap());
		} else {
			switch(idx) {
    			case FailureValue<Number> fv -> System.out.println("Failed with fv " + fv.getValue());
	    		case FailureException<Number> fe -> System.out.println("Failed with fe " + fe.getException());
		    	default -> System.out.println("As written, cannot happen");
			}
		}
		
		idx = m.parse(null);
		if(idx.eval()) {
			System.out.println(idx.unwrap());
		} else {
			switch(idx) {
			    case FailureValue<Number> fv -> System.out.println("Failed with fv " + fv.getValue());
			    case FailureException<Number> fe -> System.out.println("Failed with fe " + fe.getException());
			    default -> System.out.println("As written, cannot happen");
			}
		}
		
		idx = m.parse("not there");
		if(idx.eval()) {
			System.out.println(idx.unwrap());
		} else {
			switch(idx) {
			    case FailureValue<Number> fv -> System.out.println("Failed with fv " + fv.getValue());
			    case FailureException<Number> fe -> System.out.println("Failed with fe " + fe.getException());
			    default -> System.out.println("As written, cannot happen");
			}
		}
	}
}
