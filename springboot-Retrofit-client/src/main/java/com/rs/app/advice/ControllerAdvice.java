package com.rs.app.advice;

import java.net.ConnectException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rs.app.exception.UserAlreadyAvailableException;
import com.rs.app.exception.UserNotAvailableException;
import com.rs.app.model.ErrorResponse;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { UserAlreadyAvailableException.class })
	public ResponseEntity<ErrorResponse> handleUserAlreadyAvailableException(UserAlreadyAvailableException uaaex) {
		String error = "User already exists! please try with another one";
		ErrorResponse eresp = new ErrorResponse(System.currentTimeMillis(), HttpStatus.CONFLICT, uaaex.getMessage(),
				error);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(eresp);
	}

	@ExceptionHandler(value = { UserNotAvailableException.class })
	public ResponseEntity<ErrorResponse> handleUserAlreadyAvailableException(UserNotAvailableException unaex) {
		String error = "User not exists! please try with another one";
		ErrorResponse eresp = new ErrorResponse(System.currentTimeMillis(), HttpStatus.NOT_FOUND, unaex.getMessage(),
				error);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(eresp);
	}
	
	@ExceptionHandler(value = {ConnectException.class})
	public ResponseEntity<ErrorResponse> handleConnectException(ConnectException cex) {
		String error = "Connection problem try after sometime";
		ErrorResponse eresp = new ErrorResponse(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR, cex.getMessage(),
				error);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(eresp);
	}
}