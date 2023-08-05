package com.huwevans.darien.impl;

import com.huwevans.darien.Success;

public class SuccessImpl<T> implements Success<T> {
	protected T value;
	
	protected SuccessImpl() {
	}
	
	public SuccessImpl(T value) {
		this.value = value;
	}
	
	public boolean eval() {
		return true;
	}
	
	public T unwrap() {
		return this.value;
	}
}
