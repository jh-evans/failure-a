package com.huwevans.darien.nogeneric.types.impl;

import com.huwevans.darien.nogeneric.types.FailurePartialResult;

public class FailurePartialResultImpl implements FailurePartialResult {
	private Object t;
	
	public FailurePartialResultImpl(Object t) {
		this.t = t;
	}

	public boolean eval() {
		return false;
	}
	
	public Object getPartialResult() {
		return this.t;
	}
	
	@Override
	public Object unwrap() {
		return null;
	}
}
