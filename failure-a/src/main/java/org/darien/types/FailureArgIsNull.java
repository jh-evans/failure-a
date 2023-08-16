package org.darien.types;

import java.util.List;

/** This type is used by the implementation of org.darien.types.utils.FailureUtils to capture information about a method
 * parameter that is false in the idx index position {@link org.darien.types.utils.FailureUtils}.
 * <p>
 * Using section at <a href="https://darien-project.readthedocs.io/en/latest/using.html">The Darien Project Documentation</a>.
 */
public interface FailureArgIsNull extends F {
	
	/**
	 * Gets the indices.
	 *
	 * @return the indices of the method parameters that have failed
	 */
	public List<Number> getIndices();
}
