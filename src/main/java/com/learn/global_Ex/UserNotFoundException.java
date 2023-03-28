package com.learn.global_Ex;

public class UserNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	private String details;

	public UserNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	
	

}
