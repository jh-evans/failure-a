package org.darien.types;

/** This type is used to wrap a value that fails a run-time argument check, e.g., a URL parameter that does not start
 * with 'http:'
 * <p>
 * Using section at <a href="https://darien-project.readthedocs.io/en/latest/using.html">The Darien Project Documentation</a>.
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