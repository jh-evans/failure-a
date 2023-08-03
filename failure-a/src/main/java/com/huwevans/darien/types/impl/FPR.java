package com.huwevans.darien.types.impl;

import com.huwevans.darien.types.FailurePartialResult;

public class FPR extends Failure implements FailurePartialResult {
	public FPR(Object value) {
		this.value = value;
	}
	public Object getPartialResult() {
		return this.value;
	}
}
