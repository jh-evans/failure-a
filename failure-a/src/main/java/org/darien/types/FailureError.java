package org.darien.types;

/* This type is used to wrap the Java error types (FailureException works with Java exceptions). There is
 * no type that works with Throwable as Java errors and Java exceptions model two different failure types in
 * such different ways
 */
public interface FailureError extends F {
	public Error getError();
}
