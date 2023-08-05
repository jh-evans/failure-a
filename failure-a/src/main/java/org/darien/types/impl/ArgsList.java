package org.darien.types.impl;

import java.util.ArrayList;
import java.util.List;

import org.darien.types.FailureArgIsNull;

public class ArgsList extends Failure {
    protected List<Number> idxs;
    protected StackTraceElement ste;
	
	public ArgsList() {
		this.idxs = new ArrayList<Number>();
		this.ste = new Exception().getStackTrace()[2];
	}
	
	public void addIndex(Number idx) {
		this.idxs.add(idx);
	}
}
