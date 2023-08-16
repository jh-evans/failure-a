package org.darien.types.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a subclass of the type {@link org.darien.types.impl.Failure}. Do not rely on any implementation details herein.
 * This class handles tracking where in your code a Failure subclass is created.
 * This class is abstract as it is not intended to be instantiated, but used by a subclass, e.g., FAIF or FAIN
 * This is the superclass for {@link org.darien.types.impl.FAIF} and {@link org.darien.types.impl.FAIN}
 * <p>
 * Using section at <a href="https://darien-project.readthedocs.io/en/latest/using.html">The Darien Project Documentation</a>.
 */

public abstract class ArgsList extends Failure {
	/**
	 * The list of method parameter indices involved in a failure. Protected for use by all subclasses, e.g., FAIF and FAIN
	 */
    protected List<Number> idxs;
    
	/**
	 * Used for the location in your code where this error occurred. Protected for use by all subclasses, e.g., FAIF and FAIN
	 */
    protected StackTraceElement ste;
	
	/**
	 * Create an ArgsList to be used by subclasses
	 */
    
	public ArgsList() {
		this.idxs = new ArrayList<Number>();
		this.ste = new Exception().getStackTrace()[3];
	}

	/**
	 * A parameter index is recorded as failing, e.g., false or null.
	 * 
	 * @param idx - The index number (starting at 0) of a method parameter that has failed a check, e.g., is null or failed a boolean test
	 */
	public void addIndex(Number idx) {
		this.idxs.add(idx);
	}

	/**
	 * Return the error location where false or null used incorrectly in the method that might fail, e.g., a null parameter.
	 * 
	 * @param o - The object to be tested and its result added to the String result as explained below
	 * @return the error location based on the passed object. If the object is null, the result will contain 'null' in the result.
	 *         If o is a Boolean false, the String result will contain 'false'
	 */
	protected String getLocation(Object o) {
		String msg = "";
		msg = "Args " + o + " at " + ste.getClassName() + "." + ste.getMethodName() + "(" + ste.getFileName() + ":" + ste.getLineNumber() + ")";
		msg += " " + o + " in arg " + ((this.idxs.size() == 1) ? "position " : "positions ") + this.idxs;
		return msg;
	}
}
