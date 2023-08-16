package org.darien.types.utils;

import org.darien.types.FailureArgIsFalse;
import org.darien.types.FailureArgIsNull;
import org.darien.types.impl.FAIF;
import org.darien.types.impl.FAIN;

/** A utility class to  help you determine if method parameters are valid
 * 
 */

public class FailureUtils {
	
	/**
	 * Prevent the instantiation of FailureUtils so the static methods are used.
	 */
	private FailureUtils() {
		
	}
	
	private static boolean oneOf(Object t, Object[] args) {		
		for(Object o : args) {
			if(o == t) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Returns true if one of its arguments is null.
	 *
	 * @param args -  The objects to test for being null
	 * @return return true if one of the arguments is null
	 */
	public static boolean oneIsNull(Object... args) {
		if(args == null) {
			return true;
		}
		return oneOf(null, args);
	}
	
	/**
	 * Returns true if one of its arguments is false.
	 *
	 * @param args - The objects to test for being false
	 * @return return true if one of the arguments is false
	 */
	public static boolean oneIsFalse(Boolean... args) {
		if(args == null) {
			return false;
		}
		
		return oneOf(false, args);
	}
	
	/**
	 * Returns a list of the indices that indicate which of the arguments is null.
	 *
	 * @param args - The objects to test for being null
	 * @return return a list of indices indicating which of the arguments was null
	 */
	public static FailureArgIsNull theNull(Object... args) {
		FAIN fain = new FAIN();
		
		if(args == null) {
			return fain;
		}
		
		for(int i = 0; i < args.length; i++) {
			if(args[i] == null) {
				fain.addIndex(i);
			}
		}
		
		return fain;
	}
	
	/**
	 * Returns a list of the indices that indicate which of the arguments is false.
	 *
	 * @param args - The objects to test for being false
	 * @return return a list of indices indicating which of the arguments was false
	 */
	public static FailureArgIsFalse theFalse(Boolean... args) {
		FailureArgIsFalse faif = new FAIF();
		
		if(args == null) {
			return faif;
		}
		
		for(int i = 0; i < args.length; i++) {
			if(args[i] == false) {
				faif.addIndex(i);
			}
		}
		
		return faif;
	}
}
