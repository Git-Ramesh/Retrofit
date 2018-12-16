package com.rs.app.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse implements Serializable {
	private static final long serialVersionUID = 4210553308270355779L;
	private long timestamp;
	private HttpStatus status;
	private String message;
	private List<String> errors;
	
	public ErrorResponse(long timestamp, HttpStatus status, String message, String... errors) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.message = message;
		this.errors = Arrays.asList(errors);
	}
	
	
}
