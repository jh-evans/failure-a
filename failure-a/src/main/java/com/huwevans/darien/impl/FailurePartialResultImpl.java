package com.huwevans.darien.impl;

import com.huwevans.darien.FailurePartialResult;

public class FailurePartialResultImpl<T> implements FailurePartialResult<T>  {
	private T t;
	public FailurePartialResultImpl(T t) {
		this.t = t;
	}

	public boolean eval() {
		return false;
	}
	
	public T getPartialResult() {
		return this.t;
	}
	
	public T unwrap() {
		return this.getPartialResult();
	}
}
