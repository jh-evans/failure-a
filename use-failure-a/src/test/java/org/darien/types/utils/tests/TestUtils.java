package org.darien.types.utils.tests;

import java.lang.reflect.Field;

import org.darien.types.S;
import org.darien.types.impl.FErr;
import org.darien.types.impl.FExp;
import org.darien.types.impl.Success;
import org.darien.types.utils.FailureUtils;

public class TestUtils {

	public static S getField(String cn, String fn, Object inst) {
        if(FailureUtils.oneIsNull(cn, fn, inst)) {
        	return FailureUtils.theNull(cn, fn, inst);
        }
    	try {
    		Class<?> cls = Class.forName(cn);
    		Field fld = cls.getDeclaredField(fn);
    		fld.setAccessible(true);
    		return new Success(fld.get(inst));
    	} catch (ExceptionInInitializerError eiie) {
        	return new FErr(eiie);
    	} catch(ClassNotFoundException cnfe) {
    		return new FExp(cnfe);
    	} catch (NoSuchFieldException nsfe) {
    		return new FExp(nsfe);
		} catch (SecurityException se) {
    		return new FExp(se);
		} catch (IllegalArgumentException ile) {
    		return new FExp(ile);
		} catch (NullPointerException npe) {
    		return new FExp(npe);
		} catch (IllegalAccessException iae) {
    		return new FExp(iae);
		}
	}
}