package org.darien.types.impl;

import org.darien.types.FailureArgIsNull;

public class FAIN extends ArgsList implements FailureArgIsNull {	
	public String getLocation() {
		String msg = "";
		msg = "Args null at " + ste.getClassName() + "." + ste.getMethodName() + "(" + ste.getFileName() + ":" + ste.getLineNumber() + ")";
		msg += " null in arg " + ((this.idxs.size() == 1) ? "position " : "positions ") + this.idxs;
		return msg;
	}
}
