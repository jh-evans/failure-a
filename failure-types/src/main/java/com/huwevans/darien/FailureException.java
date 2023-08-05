package com.huwevans.darien;

public interface FailureException extends Failure {
	public Exception getException();
}
