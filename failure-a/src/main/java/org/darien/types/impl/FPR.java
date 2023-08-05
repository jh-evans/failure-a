package org.darien.types.impl;

import org.darien.types.FailurePartialResult;

public class FPR extends Failure implements FailurePartialResult {
	public FPR(Object value) {
		this.value = value;
	}
	public Object getPartialResult() {
		return this.value;
	}
}
