package com.huwevans.darien.types.impl;

public class FPR extends Failure {
	public FPR(Object value) {
		this.value = value;
	}
	public Object getPartialResult() {
		return this.value;
	}
}
