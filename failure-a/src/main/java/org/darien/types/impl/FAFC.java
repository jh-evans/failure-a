package org.darien.types.impl;

import org.darien.types.FailureArgFailsCheck;

// TODO: Auto-generated Javadoc
/**
 * Wraps an object that has failed a method argument check, e.g., a URL parameter was passed that uses 'http' and not 'https'.
 * <p>
 * Using section at <a href="https://darien-project.readthedocs.io/en/latest/using.html">The Darien Project Documentation</a>.
 */

public class FAFC extends Failure implements FailureArgFailsCheck {
	
	private Object failed;
	
	/**
	 * Instantiates a new implementation for {@link org.darien.types.FailureArgFailsCheck}.
	 *
	 * @param failed - The object that represents the method parameter that failed
	 */
	public FAFC(Object failed) {
		this.failed = failed;
	}
	
	/**
	 * Gets the object that represents the argument that failed.
	 *
	 * @return the arg
	 */
	public Object getArg() {
		return this.failed;
	}
}
