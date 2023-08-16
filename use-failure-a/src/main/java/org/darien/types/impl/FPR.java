package org.darien.types.impl;

import org.darien.types.FailurePartialResult;

/**
 * This is a subclass of the type {@link org.darien.types.impl.Failure}. Do not rely on any implementation details herein.
 * <p>
 * Using section at <a href="https://darien-project.readthedocs.io/en/latest/using.html">The Darien Project Documentation</a>.
 */

public class FPR extends Failure implements FailurePartialResult {

	/** Construct a wrapped partial result.
	 */	
	
	public FPR(Object value) {
		this.value = value;
	}
	
	/** Return the wrapped value.
	 */	
	public Object getPartialResult() {
		return this.value;
	}
}
