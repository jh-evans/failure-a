package org.darien.types;

/* This type is used by the implementation of org.darien.types.utils.FailureUtils to capture information about a method
 * parameter that is false in the idx index position (FailureUtils.oneIsFalse) when used with FailureUtils.theFalse
 * 
 */
public interface FailureArgIsFalse extends F {
	public void addIndex(Number idx);
}
