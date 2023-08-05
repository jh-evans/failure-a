package org.darien.types.impl;

import org.darien.types.FailureArgIsNull;

public class FAIN extends ArgsList implements FailureArgIsNull {	
	public String getLocation() {
		return super.getLocation(null);
	}
}
