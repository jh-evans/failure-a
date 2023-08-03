package com.huwevans.darien.types.utils;

import com.huwevans.darien.types.FailureArgIsFalse;
import com.huwevans.darien.types.FailureArgIsNull;
import com.huwevans.darien.types.impl.FAIF;
import com.huwevans.darien.types.impl.FAIN;

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
		return oneOf(null, args);
	}
	
	public static boolean oneIsFalse(boolean... args) {
		for(boolean b : args) {
			if(b == false) {
				return true;
			}
		}
		
		return false;
	}
	
	public static FailureArgIsNull theNull(Object... args) {
		FailureArgIsNull fain = new FAIN();
		
		for(int i = 0; i < args.length; i++) {
			if(args[i] == null) {
				fain.addNull(i);
			}
		}
		
		return fain;
	}
	
	public static FailureArgIsFalse theFalse(boolean... args) {
		FailureArgIsFalse faif = new FAIF();		
		for(int i = 0; i < args.length; i++) {
			if(args[i] == false) {
				faif.addFalse(i);
			}
		}
		
		return faif;
	}
}
