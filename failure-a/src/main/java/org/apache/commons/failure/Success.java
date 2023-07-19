package org.apache.commons.failure;

public class Success<T> {
	private T value;
	
	protected Success() {
	}
	
	public Success(T value) {
		this.value = value;
	}
	
	public boolean eval() {
		return true;
	}
	
	public T unwrap() {
		return this.value;
	}
}