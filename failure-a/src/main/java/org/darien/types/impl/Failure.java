package org.darien.types.impl;

/**
 * This is an implementation of the type {@link org.darien.types.F}. Do not rely on any implementation details herein.
 * <p>
 * Using section at <a href="https://darien-project.readthedocs.io/en/latest/using.html">The Darien Project Documentation</a>.
 */

public abstract class Failure extends Success {
	/** The location in your code where this error object was created.
	 */
	private String errorLocation;
	
	/** The no-arg constructor take the classname, methodname, filename, and location of where this
	 * object was created, enabling you to more quickly diagnose issues.
	 */
	public Failure() {
		StackTraceElement ste = new Exception().getStackTrace()[2];
		this.errorLocation = ste.getClassName() + "." + ste.getMethodName() + "(" + ste.getFileName() + ":" + ste.getLineNumber() + ")";
	}

	/** The location of the failure in your code
	 */
	public String getLocation() {
		return errorLocation;
	}
	
	@Override
	public boolean eval() {
		return false;
	}

	@Override
	public Object unwrap() {
		throw new UnsupportedOperationException();
	}
}
