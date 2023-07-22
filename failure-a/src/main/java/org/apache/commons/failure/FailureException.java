package org.apache.commons.failure;

public class FailureException<T> extends Failure<T> {
	Exception e;
	
	public FailureException(Exception e) {
		this.e = e;
	}
	
	public Exception getException() {
		return this.e;
	}
}
