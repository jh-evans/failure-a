package org.darien.types.impl;

import org.darien.types.FailureException;

public class FExp extends Failure implements FailureException {
	Exception e;
	StackTraceElement[] ste;
	
	public FExp(Exception e) {
		this.e = e;
		this.ste = new Exception().getStackTrace();
	}
	
	public Exception getException() {
		return this.e;
	}
	
	public String errorLocation() {
		String msg = "";
		for(StackTraceElement ste: this.ste) {
			msg += ste.toString() + "\n";
		}
		return msg;
	}
}
