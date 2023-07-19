package org.apache.commons.failure;

public class FailurePartialResult<T> extends Failure<T> {	
	public FailurePartialResult(T t) {
		super(t);
	}
	
	public T getPartialResult() {
		return this.unwrap();
	}
}
