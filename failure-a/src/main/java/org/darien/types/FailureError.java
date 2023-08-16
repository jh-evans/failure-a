package org.darien.types;

/** This type is used to wrap the Java error types (FailureException works with Java exceptions). There is
 * no type that works with Throwable as Java errors and Java exceptions model two different failure types in
 * such different ways.
 * <p>
 * Using section at <a href="https://darien-project.readthedocs.io/en/latest/using.html">The Darien Project Documentation</a>.
 */

public interface FailureError extends F {

	/**
	 * Return the java.lang.Error subclass in your code that caused a method to fail.
	 * 
	 * @return The error caught in your code that may fail and returned by it.
	 * @since 1.0.0
	 */
	public Error getError();
}
