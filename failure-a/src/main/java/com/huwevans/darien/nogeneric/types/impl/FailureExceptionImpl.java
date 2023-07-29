package com.huwevans.darien.nogeneric.types.impl;

import com.huwevans.darien.nogeneric.types.FailureException;

public class FailureExceptionImpl implements FailureException {
	Exception e;
	StackTraceElement[] ste;
	
	public FailureExceptionImpl(Exception e) {
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
	
	public boolean eval() {
		return false;
	}
	
	public Object unwrap() {
		return null;
	}
}
