package org.apache.commons.failure;

public abstract class Failure<T> extends Success<T> {
	protected Failure() {
	}
	
	public Failure(T t) {
		super(t);
	}
	
	@Override
	public boolean eval() {
		return false;
	}
}
