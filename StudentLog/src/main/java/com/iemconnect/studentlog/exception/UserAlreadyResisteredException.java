package com.iemconnect.studentlog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserAlreadyResisteredException extends RuntimeException{
	
	public UserAlreadyResisteredException (String message) {
		super(message);
	}
}
