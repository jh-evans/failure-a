package org.darien.types.utils;

import org.darien.types.FailureArgIsFalse;
import org.darien.types.FailureArgIsNull;
import org.darien.types.impl.FAIF;
import org.darien.types.impl.FAIN;

public class FailureUtils {
	private static boolean oneOf(Object t, Object[] args) {		
		for(Object o : args) {
			if(o == t) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean oneIsNull(Object... args) {
		if(args == null) {
			return true;
		}
		return oneOf(null, args);
	}
	
	public static boolean oneIsFalse(Boolean... args) {
		if(args == null) {
			return false;
		}
		
		return oneOf(false, args);
	}
	
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
