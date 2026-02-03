package com.mikaelmarques.workshopJpa.resources.exceptions;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class ValidationError extends StandardError{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String, String> fieldErrors = new HashMap<>();
	
	public ValidationError() {}
	
	public ValidationError(Instant timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}
	
	public Map<String, String> getFieldErrors() {
		return fieldErrors;
	}

	public void addError(String fieldName, String message) {
		fieldErrors.put(fieldName, message);
	}
}
