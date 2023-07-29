package com.huwevans.darien.nogeneric.types;

public interface FailureException extends Failure {
	public Exception getException();
}
