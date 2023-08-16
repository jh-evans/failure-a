package org.darien.tools.codegen;

/** For loading classes by {@link org.darien.tools.codegen.CodeGen}
 */

public class CodeGenClassLoader extends ClassLoader {

	/**
	 * Create a CodeGenClassLoader for use with loadClass
	 */
	public CodeGenClassLoader() {
	}
	/**
	 * A parameter index is recorded as failing, e.g., false or null.
	 * <p>
	 * See <a href="https://docs.oracle.com/javase/8/docs/api/java/lang/ClassLoader.html#loadClass-java.lang.String-boolean-"> Oracle doc </a> for more information.
	 * 
	 * @param name - The fully qualified name of the class to be loaded
	 * @param resolve - whether the class is resolved or not
	 */
	public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
		return super.loadClass(name, resolve);
	}
}
