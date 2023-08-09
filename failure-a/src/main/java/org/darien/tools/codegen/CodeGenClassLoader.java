package org.darien.tools.codegen;

public class CodeGenClassLoader extends ClassLoader {

	public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
		return super.loadClass(name, resolve);
	}
}
