package com.iemconnect.studentlog.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundCustomException extends RuntimeException {
	
	public UserNotFoundCustomException(String message) {
		super(message);
		
	}

}