package org.darien.types;

/** This type is used by the implementation of org.darien.types.utils.FailureUtils to capture information about a method
 * parameter that is false in the idx index position {@link org.darien.types.utils.FailureUtils}.
 * <p>
 * Using section at <a href="https://darien-project.readthedocs.io/en/latest/using.html">The Darien Project Documentation</a>.
 */
public interface FailureArgIsFalse extends F {
	/**
	 * @return Add which index (starting at 0) a parameter within a list passed to your method that is false
	 * @since 1.0.0
	 */
	public void addIndex(Number idx);
}
