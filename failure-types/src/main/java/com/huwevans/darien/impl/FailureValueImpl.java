package com.huwevans.darien.impl;

import com.huwevans.darien.FailureValue;

public class FailureValueImpl implements FailureValue {
	Object obj;
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

	public Object unwrap() {
		return null;
	}
}