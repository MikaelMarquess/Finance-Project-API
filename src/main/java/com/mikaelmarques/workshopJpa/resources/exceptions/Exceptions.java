package com.mikaelmarques.workshopJpa.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mikaelmarques.workshopJpa.services.exceptions.ForbiddenException;
import com.mikaelmarques.workshopJpa.services.exceptions.ResourceNotFound;
import com.mikaelmarques.workshopJpa.services.exceptions.UnauthorizedAccessException;
import com.mikaelmarques.workshopJpa.services.exceptions.UserAlreadyExistsException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class Exceptions {
	
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFound e, HttpServletRequest request){
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<StandardError> ForbiddenException(ForbiddenException e, HttpServletRequest request){
		String error = "Request not allowed.";
		HttpStatus status = HttpStatus.FORBIDDEN;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<StandardError> UserAlreadyExistsException(UserAlreadyExistsException e, HttpServletRequest request){
		String error = "Bad request.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(UnauthorizedAccessException.class)
	public ResponseEntity<StandardError> UnauthorizedAccessException(UnauthorizedAccessException e, HttpServletRequest request){
		String error = "Bad request.";
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> InvalidFormException(MethodArgumentNotValidException e, HttpServletRequest request){
		String error = "Bad request.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ValidationError err = new ValidationError(
				Instant.now(), 
				status.value(), 
				error, 
				"validation error",
				request.getRequestURI()
				);
		
		e.getBindingResult().getFieldErrors().forEach(fe -> err.addError(fe.getField(), fe.getDefaultMessage()));
		return ResponseEntity.status(status).body(err);
	}
}
