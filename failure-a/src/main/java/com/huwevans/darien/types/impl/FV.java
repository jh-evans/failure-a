package com.huwevans.darien.types.impl;

public class FV extends Failure {
	Number n;
		
	public FV(Number n) {
		this.n = n;
	}

	public Number getValue() {
		return this.n;
	}
}