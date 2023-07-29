package com.huwevans.darien.nogeneric.types.impl;

import com.huwevans.darien.nogeneric.types.FailureException;

public class FailureExceptionImpl implements FailureException {
	Exception e;
	
	public FailureExceptionImpl(Exception e) {
		this.e = e;
	}
	
	public Exception getException() {
		return this.e;
	}
	
	public boolean eval() {
		return false;
	}
	
	public Object unwrap() {
		return null;
	}
}
