package org.darien.types.impl;

import org.darien.types.FailureArgIsFalse;

public class FAIF extends ArgsList implements FailureArgIsFalse {	
	public String getLocation() {
		String msg = "";
		msg = "Args false at " + ste.getClassName() + "." + ste.getMethodName() + "(" + ste.getFileName() + ":" + ste.getLineNumber() + ")";
		msg += " false in arg " + ((this.idxs.size() == 1) ? "position " : "positions ") + this.idxs;
		return msg;
	}
}
