package com.huwevans.darien.nogeneric.types;

public interface FailurePartialResult extends Failure  {
	public Object getPartialResult();
	public Object unwrap();
}
