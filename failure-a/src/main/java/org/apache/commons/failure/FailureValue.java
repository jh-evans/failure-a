package org.apache.commons.failure;

public class FailureValue<T> extends Failure<T> {
	Number n;
	
	public FailureValue(Number n) {
		this.n = n;
	}

	public Number getValue() {
		return this.n;
	}
}