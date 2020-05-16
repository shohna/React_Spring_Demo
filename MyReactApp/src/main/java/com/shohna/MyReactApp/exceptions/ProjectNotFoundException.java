package com.shohna.MyReactApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectNotFoundException extends RuntimeException{

	public ProjectNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
