package org.darien.types.impl;

import java.util.Arrays;

import org.darien.types.FailureException;

/**
 * This is a subclass of the type {@link org.darien.types.impl.Failure}. Do not rely on any implementation details herein.
 * <p>
 * Using section at <a href="https://darien-project.readthedocs.io/en/latest/using.html">The Darien Project Documentation</a>.
 */

public class FExp extends Failure implements FailureException {
	Exception e;
	StackTraceElement[] ste;

	/**
	 *  Construct a wrapped exception.
	 *
	 * @param e the exception to wrap.
	 */	
	public FExp(Exception e) {
		this.e = e;
		this.ste = new Exception().getStackTrace();
	}

	/**
	 *  Return the wrapped exception.
	 *
	 * @return the exception
	 */	
	public Exception getException() {
		return this.e;
	}
	
	/**
	 *  Overrides getLocation on {@link org.darien.types.impl.Failure} to return the whole stacktrace.
	 *
	 * @return the stacktrace
	 */	
	@Override
	public String getLocation() {
		String msg = ste[0].toString() + "\n";
		msg += ste[1].toString() + " **** Call to FExp constructor is here **** \n";
		
		StackTraceElement[] dest = new StackTraceElement[ste.length - 2];
		System.arraycopy(ste, 2, dest, 0, ste.length - 2);
		for(StackTraceElement elem : Arrays.asList(dest)) {
		    msg += elem.toString() + "\n";
		}
		return msg;
	}
}