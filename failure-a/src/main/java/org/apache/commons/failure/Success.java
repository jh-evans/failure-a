package org.apache.commons.failure;

public interface Success<T> {
	public boolean eval();
	public T unwrap();
}
