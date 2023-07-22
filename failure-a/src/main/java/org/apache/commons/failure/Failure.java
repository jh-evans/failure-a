package org.apache.commons.failure;

public abstract class Failure<T> extends Success<T> {
	protected Failure() {
	}
	
	private Failure(T t) {
	}
	
	@Override
	public boolean eval() {
		return false;
	}
}
