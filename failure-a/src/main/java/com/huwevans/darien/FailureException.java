package org.apache.commons.failure;

public interface FailureException<T> extends Failure<T> {
	public Exception getException();
}
