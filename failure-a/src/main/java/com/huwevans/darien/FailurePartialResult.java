package org.apache.commons.failure;

public interface FailurePartialResult<T> extends Failure<T>  {
	public T getPartialResult();
	public T unwrap();
}
