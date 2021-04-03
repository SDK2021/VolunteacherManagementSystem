package com.volunteacher.app.exception;

public class UnauthorizedUserException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UnauthorizedUserException(String error) {
		super(error);
	}
}
