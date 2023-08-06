package org.darien.types.impl;

import org.darien.types.FailureError;

public class FErr extends Failure implements FailureError {
	Error e;
	StackTraceElement[] ste;
	
	public FErr(Error e) {
		this.e = e;
		this.ste = new Exception().getStackTrace();
	}

	public Error getError() {
		return this.e;
	}
	
	public String errorLocation() {
		String msg = "";
		for(StackTraceElement ste: this.ste) {
			msg += ste.toString() + "\n";
		}
		return msg;
	}
}
