package org.darien.types.impl;

import org.darien.types.S;

/**
 * This is an implementation of the type {@link org.darien.types.S}. Do not rely on any implementation details herein.
 * <p>
 * Using section at <a href="https://darien-project.readthedocs.io/en/latest/using.html">The Darien Project Documentation</a>.
 */

public class Success implements S {
	/** The wrapped object passed back from your code that may fail.
	 */
	protected Object value;

	/** Make the no-arg constructor protected so that it cannot be used externally.
	 *
	 */
	protected Success() {
	}


	/** Wrap the object to be passed back from your code that may fail.
	 *
	 */
	public Success(Object value) {
		this.value = value;
	}
	
	public boolean eval() {
		return true;
	}
	
	public Object unwrap() {
		return this.value;
	}
}