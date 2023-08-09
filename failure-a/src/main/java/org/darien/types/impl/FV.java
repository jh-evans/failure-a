package org.darien.types.impl;

import org.darien.types.FailureValue;

/**
 * This is a subclass of the type {@link org.darien.types.impl.Failure}. Do not rely on any implementation details herein.
 * <p>
 * Using section at <a href="https://darien-project.readthedocs.io/en/latest/using.html">The Darien Project Documentation</a>.
 */

public class FV extends Failure implements FailureValue {
	/** The wrapped value representing failure in your code.
	 */
	Number n;

	/** Construct a wrapped value.
	 */	
	public FV(Number n) {
		this.n = n;
	}

	/** Return the wrapped value.
	 */	
	public Number getValue() {
		return this.n;
	}
}