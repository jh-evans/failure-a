package org.darien.types;

/** This is the supertype of all Failure objects. eval() of all failure types returns false.
 *
 * <p>See <a href="https://darien-project.readthedocs.io/en/latest/using.html">how to use the Darien Poject</a>.
 */

public interface F extends S {

	/**
	 * <p>get the location of where the failure instance was created in your code. This will tell you the
	 * line of code to check should your code fail.
	 * </p>
	 * @return A String with the class and methodname location of the failure, the filename and line number.
	 * @since 1.0.0
	 */
	public String getLocation();
}
