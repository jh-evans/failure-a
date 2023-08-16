package org.darien.types;

/**
 * For how to use this type, see the using section at <a href="https://darien-project.readthedocs.io/en/latest/using.html">The Darien Project Documentation</a>.
 */
public interface S {
	/**
	 * <p>evaluate a Darian Project wrapper. Instances of S return true, instances of F return false.
	 * </p>
	 * @return Whether the type represents a failure or not. All S-typed instances return true
	 * @since 1.0.0
	 */
	public boolean eval();
	/**
	 * <p>unwrap a value that has been passed back from your code that may fail.
	 * </p>
	 * @return The object being returned from the method that may fail.
	 * @since 1.0.0
	 */
	public Object unwrap();
}
