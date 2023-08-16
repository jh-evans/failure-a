package org.darien.types.impl;

import org.darien.types.FailureArgFailsCheck;

/**
 * Wraps an object that has failed a method argument check, e.g., a URL parameter was passed that uses 'http' and not 'https'.
 * <p>
 * Using section at <a href="https://darien-project.readthedocs.io/en/latest/using.html">The Darien Project Documentation</a>.
 */

public class FAFC extends Failure implements FailureArgFailsCheck {
	private Object failed;
	
	public FAFC(Object failed) {
		this.failed = failed;
	}
	
	public Object getArg() {
		return this.failed;
	}
}
