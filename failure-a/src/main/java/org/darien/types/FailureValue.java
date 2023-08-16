package org.darien.types;

/** This type is used to wrap a value that indicates failure, e.g., when a REST GET call returns
 * a value outside the 200 to 299 range. The value is wrapped with the constructor and returned with getValue.
 * <p>
 * Using section at <a href="https://darien-project.readthedocs.io/en/latest/using.html">The Darien Project Documentation</a>.
 */
public interface FailureValue extends F {
	/**
	 * Return the number that represents a failure value in your code.
	 * 
	 * @return The value that represents failure in your code, e.g., -1 for no index found on a string.
	 * @since 1.0.0
	 */
	public Number getValue();
}