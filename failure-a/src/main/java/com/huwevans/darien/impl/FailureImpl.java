package com.huwevans.darien.impl;

public abstract class FailureImpl<T> extends SuccessImpl<T> {
	protected FailureImpl() {
	}
	
	private FailureImpl(T t) {
	}
	
	@Override
	public boolean eval() {
		return false;
	}
}
