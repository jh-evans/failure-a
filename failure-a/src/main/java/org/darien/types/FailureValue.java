package org.darien.types;

/* This type is is used to wrap  a value to return it as a failure, e.g., when a REST GET call returns
 * a value outside the 200 to 299 range. The value is wrapped with the constructor and returned with getValue.
 */
public interface FailureValue extends F {
	public Number getValue();
}