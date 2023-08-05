package org.darien.types.impl;

import java.util.ArrayList;
import java.util.List;

import org.darien.types.FailureArgIsNull;

public class ArgsList extends Failure {
    protected List<Number> idxs;
    protected StackTraceElement ste;
	
	public ArgsList() {
		this.idxs = new ArrayList<Number>();
		this.ste = new Exception().getStackTrace()[3];
	}
	
	public void addIndex(Number idx) {
		this.idxs.add(idx);
	}
	
	protected String getLocation(Object o) {
		String msg = "";
		msg = "Args " + o + " at " + ste.getClassName() + "." + ste.getMethodName() + "(" + ste.getFileName() + ":" + ste.getLineNumber() + ")";
		msg += " " + o + " in arg " + ((this.idxs.size() == 1) ? "position " : "positions ") + this.idxs;
		return msg;
	}
}
