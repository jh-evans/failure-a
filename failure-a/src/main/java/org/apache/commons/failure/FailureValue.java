package org.apache.commons.failure;

public interface FailureValue<T> extends Failure<T> {
	public Number getValue();
}