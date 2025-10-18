package com.devfernandes.dscommerce.entities.services.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidetionError extends CustumError {
	
	
	private List<FieldMessage> errors = new ArrayList<>();
	
	
	public ValidetionError(Instant timestamp, Integer status, String error, String path) {
		super(timestamp, status, error, path);
	}


	public List<FieldMessage> getList() {
		return errors;
	}

public void addError(String fieldName, String message) {
	
	errors.add(new FieldMessage(fieldName, message));
}
		

}
