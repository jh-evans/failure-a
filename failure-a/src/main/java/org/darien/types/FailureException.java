package org.darien.types;

/** This type is used to wrap the Java Exception types (FailureError works with Java errors). There is
 * no type that works with Throwable as Java errors and Java exceptions model two different failure types in
 * such different ways.
 * 
 * Using section at <a href="https://darien-project.readthedocs.io/en/latest/using.html">The Darien Project Documentation</a>.
 */
public interface FailureException extends F {
	
	/**
	 * Gets the exception that caused your method call to fail.
	 *
	 * @return The exception caught in your code that may fail and returned by it.
	 * @since 1.0.0
	 */
	public Exception getException();
}
