package com.huwevans.darien.types.impl;

import com.huwevans.darien.types.S;

public class Success implements S {
	protected Object value;
	
	protected Success() {
	}
	
	public Success(Object value) {
		this.value = value;
	}
	
	public boolean eval() {
		return true;
	}
	
	public Object unwrap() {
		return this.value;
	}
}