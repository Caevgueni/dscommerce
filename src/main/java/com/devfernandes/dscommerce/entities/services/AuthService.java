package com.devfernandes.dscommerce.entities.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devfernandes.dscommerce.entities.User;
import com.devfernandes.dscommerce.entities.services.exceptions.ForbiddenException;

@Service
public class AuthService {
	
	
	@Autowired
	private UserService  userservice;
	
	
	
	
	public void validateSelfOrAdmin(long userId) {
		
		User me = userservice.autheticated();
		
		if (!me.hasRole("ROLE_ADMIN") && !me.getId().equals(userId)) {
			
			throw new ForbiddenException("Access denied");
		}
	}
	

}
