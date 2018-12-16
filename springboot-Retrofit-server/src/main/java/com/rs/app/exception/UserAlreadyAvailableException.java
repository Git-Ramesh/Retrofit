package com.rs.app.exception;

public class UserAlreadyAvailableException extends RuntimeException {

	private static final long serialVersionUID = 4583371998864977235L;

	public UserAlreadyAvailableException(String message) {
		super(message);
	}
	public UserAlreadyAvailableException(String message, Throwable th) {
		super(message, th);
	}
}
