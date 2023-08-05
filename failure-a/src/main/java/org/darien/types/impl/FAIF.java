package org.darien.types.impl;

import java.util.ArrayList;
import java.util.List;

import org.darien.types.FailureArgIsFalse;

public class FAIF extends Failure implements FailureArgIsFalse {
    List<Number> idxs;
    StackTraceElement ste;
	
	public FAIF() {
		this.idxs = new ArrayList<Number>();
		this.ste = new Exception().getStackTrace()[2];
	}
	
	public void addFalse(Number idx) {
		this.idxs.add(idx);
	}
	
	public String getLocation() {
		String msg = "";
		msg = "Args false at " + ste.getClassName() + "." + ste.getMethodName() + "(" + ste.getFileName() + ":" + ste.getLineNumber() + ")";
		msg += " false in arg " + ((this.idxs.size() == 1) ? "position " : "positions ") + this.idxs;
		return msg;
	}
}
