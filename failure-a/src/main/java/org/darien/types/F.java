package org.darien.types;

/** This is the supertype of all Failure objects. The eval method of all failure types returns false.
 *
 * See the using section at <a href="https://darien-project.readthedocs.io/en/latest/using.html">The Darien Project Documentation</a>.
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
