package com.huwevans.darien.utils;

public class FailureUtils {
	public static boolean oneIsNull(Object... args) {
		for(Object o : args) {
			if(o == null) {
				return true;
			}
		}
		
		return false;
	}
	
	public static int theNull(Object... args) {
		for(int i = 0; i < args.length; i++) {
			if(args[i] == null) {
				return i;
			}
		}
		
		return -1;
	}
}
