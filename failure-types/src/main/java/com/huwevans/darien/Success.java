package com.huwevans.darien;

public interface Success<T> {
	public boolean eval();
	public T unwrap();
}
