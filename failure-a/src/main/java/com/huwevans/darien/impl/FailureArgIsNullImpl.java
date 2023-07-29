package com.huwevans.darien.impl;

import com.huwevans.darien.FailureArgIsNull;

public class FailureArgIsNullImpl<T> implements FailureArgIsNull<T> {
	T t;
	Number n;
	
	public FailureArgIsNullImpl(Number n) {
		this.n = n;
	}
	
	public Number getIndex() {
		return this.n;
	}
	
	public boolean eval() {
		return false;
	}
	
	public T unwrap() {
		return this.t;
	}
}
