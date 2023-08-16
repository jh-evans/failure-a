package org.darien.types.impl;

import org.darien.types.FailureArgIsFalse;

/**
 * This is a subclass of the type {@link org.darien.types.impl.ArgsList}. Do not rely on any implementation details herein.
 * This class is used by {@link org.darien.types.utils.FailureUtils} when false is found for method parameters.
 * <p>
 * Using section at <a href="https://darien-project.readthedocs.io/en/latest/using.html">The Darien Project Documentation</a>.
 */

public class FAIF extends ArgsList implements FailureArgIsFalse {
	
	/**
	 * Return the location message in code where false has been used in a method parameter when checking using {@link org.darien.types.utils.FailureUtils}
	 */
	public String getLocation() {
		return super.getLocation(false);
	}
}
