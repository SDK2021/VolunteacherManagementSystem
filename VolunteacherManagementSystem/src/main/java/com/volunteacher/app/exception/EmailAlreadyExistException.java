package com.volunteacher.app.exception;

public class EmailAlreadyExistException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	EmailAlreadyExistException(String error)
	{
		super(error);
	}
}
