package com.huwevans.darien.nogeneric.types.impl;

import com.huwevans.darien.nogeneric.types.FailureException;

public class FailureExceptionImpl implements FailureException {
	Exception e;
	StackTraceElement ste;
	
	public FailureExceptionImpl(Exception e) {
		this.e = e;
		this.ste = new Exception().getStackTrace()[1];
	}
	
	public Exception getException() {
		return this.e;
	}
	
	public String errorLocation() {
		return ste.getClassName() + "." + ste.getMethodName() + "(" + ste.getFileName() + ":" + ste.getLineNumber() + ")";
	}
	
	public boolean eval() {
		return false;
	}
	
	public Object unwrap() {
		return null;
	}
}
