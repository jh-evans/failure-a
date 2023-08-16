package org.darien.types.impl;

import org.darien.types.FailureException;

/**
 * This is a subclass of the type {@link org.darien.types.impl.Failure}. Do not rely on any implementation details herein.
 * <p>
 * Using section at <a href="https://darien-project.readthedocs.io/en/latest/using.html">The Darien Project Documentation</a>.
 */

public class FExp extends Failure implements FailureException {
	Exception e;
	StackTraceElement[] ste;

	/** Construct a wrapped exception.
	 */	
	public FExp(Exception e) {
		this.e = e;
		this.ste = new Exception().getStackTrace();
	}

	/** Return the wrapped exception.
	 */	
	public Exception getException() {
		return this.e;
	}
	
	/** Overrides getLocation on {@link org.darien.types.impl.Failure} to return the whole stacktrace.
	 */	
	@Override
	public String getLocation() {
		String msg = "";
		for(StackTraceElement ste: this.ste) {
			msg += ste.toString() + "\n";
		}
		return msg;
	}
}
