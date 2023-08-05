package com.huwevans.darien.impl;

public abstract class FailureImpl extends SuccessImpl {
	protected FailureImpl() {
	}
	
	@Override
	public boolean eval() {
		return false;
	}
}
