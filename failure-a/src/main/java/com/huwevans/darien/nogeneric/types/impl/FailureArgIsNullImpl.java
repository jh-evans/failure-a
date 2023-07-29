package com.huwevans.darien.nogeneric.types.impl;

import com.huwevans.darien.nogeneric.types.FailureArgIsNull;

public class FailureArgIsNullImpl implements FailureArgIsNull {
	StackTraceElement ste;
	Number n;
	
	public FailureArgIsNullImpl(Number n, StackTraceElement ste) {
		this.n = n;
		this.ste = ste;
	}
	
	public Number getIndex() {
		return this.n;
	}
	
	public boolean eval() {
		return false;
	}
	
	public String toString() {
		return "Arg [" + n + "] is null at " + ste.getClassName() + "." + ste.getMethodName() + "(" + ste.getFileName() + ":" + ste.getLineNumber() + ")";
	}

	@Override
	public Object unwrap() {
		return null;
	}
}
