package org.darien.types;

/* This failure type models when code returns a partial result, not an out-and-out FailureValue, but
 * a result that partially represents a successful outcome, e.g., when calling a remote service and some values, but
 * not all those required, are returned.
 */
public interface FailurePartialResult extends F  {
	public Object getPartialResult();
}
