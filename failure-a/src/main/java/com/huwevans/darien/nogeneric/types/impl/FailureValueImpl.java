package com.huwevans.darien.nogeneric.types.impl;

import com.huwevans.darien.nogeneric.types.FailureValue;

public class FailureValueImpl implements FailureValue {
	Object t;
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