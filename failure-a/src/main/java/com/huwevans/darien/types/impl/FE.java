package com.huwevans.darien.types.impl;

import com.huwevans.darien.types.FailureException;

public class FE extends Failure implements FailureException {
	Exception e;
	StackTraceElement[] ste;
	
	public FE(Exception e) {
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
