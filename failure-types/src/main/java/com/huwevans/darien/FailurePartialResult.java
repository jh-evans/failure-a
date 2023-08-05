package com.huwevans.darien;

public interface FailurePartialResult extends Failure {
	public Object getPartialResult();
	public Object unwrap();
}
