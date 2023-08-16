package org.darien.types;

/** This failure type models when code returns a partial result, not an out-and-out FailureValue, but
 * a result that partially represents a successful outcome, e.g., when calling a remote service and some values, but
 * not all those required, are returned.
 * <p>
 * Using section at <a href="https://darien-project.readthedocs.io/en/latest/using.html">The Darien Project Documentation</a>.
 */
public interface FailurePartialResult extends F  {
	/**
	 * @return The object that represent partial failure.
	 * @since 1.0.0
	 */
	public Object getPartialResult();
}
