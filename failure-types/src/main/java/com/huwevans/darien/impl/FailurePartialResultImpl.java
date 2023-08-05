package com.huwevans.darien.impl;

import com.huwevans.darien.FailurePartialResult;

public class FailurePartialResultImpl implements FailurePartialResult  {
	Object obj;
	
	public FailurePartialResultImpl(Object obj) {
		this.obj = obj;
	}

	public boolean eval() {
		return false;
	}
	
	public Object getPartialResult() {
		return null;
	}
	
	public Object unwrap() {
		return null;
	}
}
