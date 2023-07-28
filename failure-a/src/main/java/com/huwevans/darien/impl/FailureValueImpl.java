package com.huwevans.darien.impl;

import com.huwevans.darien.FailureValue;

public class FailureValueImpl<T> implements FailureValue<T> {
	T t;
	Number n;
		
	public FailureValueImpl(Number n) {
		this.n = n;
	}

	public Number getValue() {
		return this.n;
	}
	
	public boolean eval() {
		return false;
	}

	public T unwrap() {
		return this.t;
	}
}