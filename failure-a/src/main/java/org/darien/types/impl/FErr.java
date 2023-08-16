package org.darien.types.impl;

import org.darien.types.FailureError;

/**
 * This is a subclass of the type {@link org.darien.types.impl.Failure}. Do not rely on any implementation details herein.
 * <p>
 * Using section at <a href="https://darien-project.readthedocs.io/en/latest/using.html">The Darien Project Documentation</a>.
 */

public class FErr extends Failure implements FailureError {
	Error e;
	StackTraceElement[] ste;

	/**
	 *  Construct a wrapped java.lang.Error.
	 *
	 * @param e - The error to wrap
	 */
	public FErr(Error e) {
		this.e = e;
		this.ste = new Exception().getStackTrace();
	}

	/**
	 *  Return the wrapped ava.lang.Error.
	 *
	 * @return the error
	 */
	public Error getError() {
		return this.e;
	}

	/**
	 *  Overrides getLocation on {@link org.darien.types.impl.Failure} to return the entire stacktrace.
	 *
	 * @return the stacktrace of where in your code this instance was created
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
