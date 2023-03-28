package com.learn.global_Ex;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalException_Handler {

	
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> dataNotFound(UserNotFoundException e, WebRequest request){
		e.setDetails(request.getDescription(false));
		return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
	}
}
