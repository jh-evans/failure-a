package com.huwevans.darien.nogeneric.types.impl;

import com.huwevans.darien.nogeneric.types.Success;

public class SuccessImpl implements Success {
	protected Object value;
	
	protected SuccessImpl() {
	}
	
	public SuccessImpl(Object value) {
		this.value = value;
	}
	
	public boolean eval() {
		return true;
	}
	
	public Object unwrap() {
		return this.value;
	}
}
