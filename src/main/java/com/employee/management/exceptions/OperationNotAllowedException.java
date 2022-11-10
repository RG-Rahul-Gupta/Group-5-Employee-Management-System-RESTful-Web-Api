package com.employee.management.exceptions;

public class OperationNotAllowedException extends RuntimeException {
	
	public OperationNotAllowedException(String message) {
		super(message);
	}

}
