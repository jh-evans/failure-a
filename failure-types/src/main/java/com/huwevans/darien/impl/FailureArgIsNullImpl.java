package com.huwevans.darien.impl;

import com.huwevans.darien.FailureArgIsNull;

public class FailureArgIsNullImpl implements FailureArgIsNull {
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
	
	public Object unwrap() {
		return null;
	}
}
