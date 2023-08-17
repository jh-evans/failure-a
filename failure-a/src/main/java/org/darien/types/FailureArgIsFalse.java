package org.darien.types;

/** Represents a method parameter that is false. See {@link org.darien.types.utils.FailureUtils#oneIsFalse(Boolean...) oneIsFalse} method.
 *
 * <p>See <a href="https://darien-project.readthedocs.io/en/latest/using.html">how to use the Darien Poject</a>.
 */
public interface FailureArgIsFalse extends F {
	/**
	 * Add the index of the failing method parameter
	 * 
	 * @param idx Add which index (starting at 0) a parameter within a list passed to your method that is false
	 * @since 1.0.0
	 */
	public void addIndex(Number idx);
}
