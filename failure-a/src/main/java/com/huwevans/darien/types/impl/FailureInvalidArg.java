package com.huwevans.darien.types.impl;

public class FailureInvalidArg extends Failure {
	private String msg;
	
	public FailureInvalidArg(String msg) {
		this.msg = msg;
	}
}
