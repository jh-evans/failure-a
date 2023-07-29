package com.huwevans.darien.nogeneric.types.impl;

public abstract class FailureImpl extends SuccessImpl {
	protected FailureImpl() {
	}
	
	@Override
	public boolean eval() {
		return false;
	}
}
