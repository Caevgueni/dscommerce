package com.devfernandes.dscommerce.entities.controller.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devfernandes.dscommerce.entities.services.exceptions.CustumError;
import com.devfernandes.dscommerce.entities.services.exceptions.DatabaseException;
import com.devfernandes.dscommerce.entities.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
	

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<CustumError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
	HttpStatus status = HttpStatus.NOT_FOUND;
	CustumError err = new CustumError(Instant.now(),status.value(), e.getMessage(), request.getRequestURI());
	return ResponseEntity.status(status).body(err);
	}
	
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<CustumError> database(DatabaseException e, HttpServletRequest request) {
	HttpStatus status = HttpStatus.BAD_REQUEST;
	CustumError err = new CustumError(Instant.now(),status.value(), e.getMessage(), request.getRequestURI());
	return ResponseEntity.status(status).body(err);
	}

}
