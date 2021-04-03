package com.volunteacher.app.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> resourceNotFound(ResourceNotFoundException error, WebRequest webRequest)
	{
		Error notfoundError = new Error(new Date(), HttpStatus.NOT_FOUND , error.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(notfoundError,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UnauthorizedUserException.class)
	public ResponseEntity<Object> unauthorizedUserException(ResourceNotFoundException error, WebRequest webRequest)
	{
		Error unauthorizedException = new Error(new Date(), HttpStatus.UNAUTHORIZED, error.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(unauthorizedException,HttpStatus.UNAUTHORIZED);
	}


}




