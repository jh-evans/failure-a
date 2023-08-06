package org.darien.types;

/* This type is used to wrap the Java Exception types (FailureError works with Java errors). There is
 * no type that works with Throwable as Java errors and Java exceptions model two different failure types in
 * such different ways
 */
public interface FailureException extends F {
	public Exception getException();
}
