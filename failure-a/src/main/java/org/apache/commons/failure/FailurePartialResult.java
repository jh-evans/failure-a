package org.apache.commons.failure;

public class FailurePartialResult<T> extends Failure<T> {
	private T t;
	public FailurePartialResult(T t) {
		this.t = t;
	}
	
	public T getPartialResult() {
		return this.t;
	}
	
	public T unwrap() {
		return this.getPartialResult();
	}
}
