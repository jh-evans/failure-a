package org.darien.types;

/** Wraps a value that fails a run-time argument check, e.g., a URL parameter that does not start
 * with 'http:'
 * 
 * <p>See <a href="https://darien-project.readthedocs.io/en/latest/using.html">how to use the Darien Poject</a>.
 */

public interface FailureArgFailsCheck extends F {
	
	/**
	 * Get the method argument that represents failure in your code
	 * 
	 * @return The value that represents failure in your code, e.g., -1 for no index found on a string.
	 * @since 1.0.0
	 */
	public Object getArg();
}