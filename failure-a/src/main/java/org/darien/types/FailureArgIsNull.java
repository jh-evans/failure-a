package org.darien.types;

import java.util.List;

/** Represents a method parameter that is null.
 * 
 * See {@link org.darien.types.utils.FailureUtils#oneIsNull(Object...) oneIsNull} method.
 *
 * <p>See <a href="https://darien-project.readthedocs.io/en/latest/using.html">how to use the Darien Poject</a>.
 */
public interface FailureArgIsNull extends F {
	
	/**
	 * Gets the indices.
	 *
	 * @return the indices of the method parameters that have failed
	 */
	public List<Number> getIndices();
}
