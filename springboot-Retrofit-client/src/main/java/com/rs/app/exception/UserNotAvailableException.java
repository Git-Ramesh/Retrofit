package com.rs.app.exception;

public class UserNotAvailableException extends RuntimeException {

	private static final long serialVersionUID = -6910624536618793865L;

	public UserNotAvailableException(String message) {
		super(message);
	}

	public UserNotAvailableException(String message, Throwable th) {
		super(message, th);
	}
}
