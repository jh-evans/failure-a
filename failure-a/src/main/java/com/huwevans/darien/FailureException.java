package com.huwevans.darien;

public interface FailureException<T> extends Failure<T> {
	public Exception getException();
}
