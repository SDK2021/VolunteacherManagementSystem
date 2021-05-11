package com.volunteacher.app.exception;

public class EmailNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmailNotFoundException(String errorMsg) {
		super(errorMsg);
	}
}
