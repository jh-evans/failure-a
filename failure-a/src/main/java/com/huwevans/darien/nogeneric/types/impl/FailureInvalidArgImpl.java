package com.huwevans.darien.nogeneric.types.impl;

import com.huwevans.darien.nogeneric.types.FailureInvalidArg;

public class FailureInvalidArgImpl implements FailureInvalidArg {	
	private String msg;
	private StackTraceElement ste;
	
	public FailureInvalidArgImpl(String msg) {
		this.msg = msg;
		this.ste = new Exception().getStackTrace()[2];
	}
	
	public boolean eval() {
		return false;
	}
	
	public String toString() {
		return msg + ". Invalid argument at " + ste.getClassName() + "." + ste.getMethodName() + "(" + ste.getFileName() + ":" + ste.getLineNumber() + ")";
	}

	public Object unwrap() {
		return null;
	}
}
