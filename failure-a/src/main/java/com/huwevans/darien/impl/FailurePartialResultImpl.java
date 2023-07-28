package org.apache.commons.failure.impl;

import org.apache.commons.failure.FailurePartialResult;

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
