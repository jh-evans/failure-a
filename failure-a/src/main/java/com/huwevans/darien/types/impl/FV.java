package com.huwevans.darien.types.impl;

import com.huwevans.darien.types.FailureValue;

public class FV extends Failure implements FailureValue {
	Number n;
		
	public FV(Number n) {
		this.n = n;
	}

	public Number getValue() {
		return this.n;
	}
}