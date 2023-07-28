package com.huwevans.darien.impl;

import com.huwevans.darien.FailureException;

public class FailureExceptionImpl<T> implements FailureException<T> {
	T t;
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
	
	public T unwrap() {
		return this.t;
	}
}
