package org.darien.types.impl;

import java.util.ArrayList;
import java.util.List;

import org.darien.types.FailureArgIsNull;

public class FAIN extends Failure implements FailureArgIsNull {
    List<Number> idxs;
    StackTraceElement ste;
	
	public FAIN() {
		this.idxs = new ArrayList<Number>();
		this.ste = new Exception().getStackTrace()[2];
	}
	
	public void addNull(Number idx) {
		this.idxs.add(idx);
	}
	
	public String getLocation() {
		String msg = "";
		msg = "Args null at " + ste.getClassName() + "." + ste.getMethodName() + "(" + ste.getFileName() + ":" + ste.getLineNumber() + ")";
		msg += " null in arg " + ((this.idxs.size() == 1) ? "position " : "positions ") + this.idxs;
		return msg;
	}
}
