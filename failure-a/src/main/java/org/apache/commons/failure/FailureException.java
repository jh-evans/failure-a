package org.apache.commons.failure;

public interface FailureException<T> extends Success<T> {
	public Exception getException();
}
