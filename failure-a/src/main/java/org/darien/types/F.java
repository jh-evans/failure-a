package org.darien.types;

/* This is the supertype of all Failure types. The eval method of all failure types returns false.
 * 
 */

public interface F extends S {
	public String getLocation();
}
