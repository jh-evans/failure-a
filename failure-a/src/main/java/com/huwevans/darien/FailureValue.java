package com.huwevans.darien;

public interface FailureValue<T> extends Failure<T> {
	public Number getValue();
}