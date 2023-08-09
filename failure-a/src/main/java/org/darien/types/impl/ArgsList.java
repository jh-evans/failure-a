package org.darien.types.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a subclass of the type {@link org.darien.types.impl.Failure}. Do not rely on any implementation details herein.
 * This class handles tracking where in your code a Failure subclass is created.
 * This is the superclass for {@link org.darien.types.impl.FAIF} and P@link org.darien.types.impl.FAIN}
 * <p>
 * Using section at <a href="https://darien-project.readthedocs.io/en/latest/using.html">The Darien Project Documentation</a>.
 */

public class ArgsList extends Failure {
    protected List<Number> idxs;
    protected StackTraceElement ste;
	
	public ArgsList() {
		this.idxs = new ArrayList<Number>();
		this.ste = new Exception().getStackTrace()[3];
	}

	/**
	 * A parameter index is recorded as false or null.
	 */
	public void addIndex(Number idx) {
		this.idxs.add(idx);
	}

	/**
	 * Return the error location where false or null used incorrectly in the method that might fail, e.g., a null parameter.
	 */
	protected String getLocation(Object o) {
		String msg = "";
		msg = "Args " + o + " at " + ste.getClassName() + "." + ste.getMethodName() + "(" + ste.getFileName() + ":" + ste.getLineNumber() + ")";
		msg += " " + o + " in arg " + ((this.idxs.size() == 1) ? "position " : "positions ") + this.idxs;
		return msg;
	}
}
