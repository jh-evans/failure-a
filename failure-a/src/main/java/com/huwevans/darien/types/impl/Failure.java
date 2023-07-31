package com.huwevans.darien.types.impl;

public abstract class Failure extends Success {	
    StackTraceElement ste;
		
	public Failure() {
		this.ste = new Exception().getStackTrace()[2];
	}

	public String getLocation() {
		return ste.getClassName() + "." + ste.getMethodName() + "(" + ste.getFileName() + ":" + ste.getLineNumber() + ")";
	}
	
	@Override
	public boolean eval() {
		return false;
	}

	@Override
	public Object unwrap() {
		throw new UnsupportedOperationException();
	}
}
