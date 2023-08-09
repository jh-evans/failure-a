package org.darien.types.impl;

import org.darien.types.FailureArgIsNull;

/**
 * This is a subclass of the type {@link org.darien.types.impl.ArgsList}. Do not rely on any implementation details herein.
 * <p>
 * Using section at <a href="https://darien-project.readthedocs.io/en/latest/using.html">The Darien Project Documentation</a>.
 */

public class FAIN extends ArgsList implements FailureArgIsNull {

	/**
	 * Return the location message in code where null has been passed to a method parameter when checking using {@link org.darien.types.utils.FailureUtils}
	 */
	public String getLocation() {
		return super.getLocation(null);
	}
}
