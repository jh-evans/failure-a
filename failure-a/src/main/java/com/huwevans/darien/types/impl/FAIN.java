package com.huwevans.darien.types.impl;

import java.util.ArrayList;
import java.util.List;

public class FAIN extends Failure {
    List<Number> idxs;
    StackTraceElement ste;
	
	public FAIN() {
		this.idxs = new ArrayList<Number>();
		this.ste = new Exception().getStackTrace()[2];
	}
	
	public void addNull(Number idx) {
		this.idxs.add(idx);
	}
	
	public String toString() {
		String msg = "";
		msg = "Args null at " + ste.getClassName() + "." + ste.getMethodName() + "(" + ste.getFileName() + ":" + ste.getLineNumber() + ")";
		msg += "\nMethod arg positions: " + this.idxs;
		return msg;
	}
}
