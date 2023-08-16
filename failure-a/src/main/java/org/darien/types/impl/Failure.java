package org.darien.types.impl;

import org.darien.types.F;

/**
 * This is an implementation of the type {@link org.darien.types.F}. Do not rely on any implementation details herein.
 * <p>
 * Using section at <a href="https://darien-project.readthedocs.io/en/latest/using.html">The Darien Project Documentation</a>.
 */

public abstract class Failure extends Success implements F {
	/** The location in your code where this error object was created.
	 */
	private String errorLocation;
	
	/** This constructor discovers the classname, methodname, filename, and file line number of where this
	 * object was created, enabling you to more quickly diagnose issues.
	 */
	public Failure() {
		StackTraceElement ste = new Exception().getStackTrace()[2];
		this.errorLocation = ste.getClassName() + "." + ste.getMethodName() + "(" + ste.getFileName() + ":" + ste.getLineNumber() + ")";
	}

	/** The location of the failure in your code
	 * 
	 *@return the location in your code where this instance was created which is the site of the failure
	 */
	
	public String getLocation() {
		return errorLocation;
	}

	/** The location of the failure in your code
	 * 
	 *@return false. evals for all subtypes of F return false
	 */
	
	@Override
	public boolean eval() {
		return false;
	}
	
	/** All implementations of unwrap on F throw UnsupportedOperationException as all failure implementations describe a failure and
	 *  unwrap returns a contained success object
	 * 
	 *@return No object as the UnsupportedOperationException (which is an unchecked run-time exception) is always thrown
	 */

	@Override
	public Object unwrap() {
		throw new UnsupportedOperationException();
	}
}
