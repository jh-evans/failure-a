package org.darien.types.impl;

import org.darien.types.FailureValue;

public class FV extends Failure implements FailureValue {
	Number n;
		
	public FV(Number n) {
		this.n = n;
	}

	public Number getValue() {
		return this.n;
	}
}