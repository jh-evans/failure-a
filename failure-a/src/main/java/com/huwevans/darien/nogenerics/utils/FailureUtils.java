package com.huwevans.darien.nogenerics.utils;

import com.huwevans.darien.nogeneric.types.FailureArgIsNull;
import com.huwevans.darien.nogeneric.types.impl.FailureArgIsNullImpl;

public class FailureUtils {
	public static boolean oneIsNull(Object... args) {
		for(Object o : args) {
			if(o == null) {
				return true;
			}
		}
		
		return false;
	}
	
	public static FailureArgIsNull theNull(Object... args) {
		Exception e = new Exception();
		StackTraceElement[] ste = e.getStackTrace();
		
		for(int i = 0; i < args.length; i++) {
			if(args[i] == null) {
				return new FailureArgIsNullImpl(i, ste[2]);
			}
		}
		
		return new FailureArgIsNullImpl(-1, ste[2]);
	}
}
