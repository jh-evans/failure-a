package org.darien.types.impl;

import org.darien.types.FailureArgIsFalse;

public class FAIF extends ArgsList implements FailureArgIsFalse {	
	public String getLocation() {
		return super.getLocation(false);
	}
}
