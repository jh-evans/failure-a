package com.huwevans.darien;

public interface FailurePartialResult<T> extends Failure<T>  {
	public T getPartialResult();
	public T unwrap();
}
