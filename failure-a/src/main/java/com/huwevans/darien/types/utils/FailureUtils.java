package com.huwevans.darien.types.utils;

import com.huwevans.darien.types.impl.FAIN;

public class FailureUtils {
	public static boolean oneIsNull(Object... args) {
		for(Object o : args) {
			if(o == null) {
				return true;
			}
		}
		
		return false;
	}
	
	public static FAIN theNull(Object... args) {
		FAIN fain = new FAIN();
		
		for(int i = 0; i < args.length; i++) {
			if(args[i] == null) {
				fain.addNull(i);
			}
		}
		
		return fain;
	}
}
