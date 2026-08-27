package com.devfernandes.dscommerce.entities.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.devfernandes.dscommerce.entities.Role;
import com.devfernandes.dscommerce.entities.User;
import com.devfernandes.dscommerce.entities.DTO.UserDTO;
import com.devfernandes.dscommerce.entities.repositories.UserRepository;
import com.devfernandes.dscommerce.projections.UserDetailsProjection;

import jakarta.transaction.Transactional;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		List<UserDetailsProjection> result = repository.searchUserAndRolesByEmail(username);

		if (result.size() == 0) {

			throw new UsernameNotFoundException("User not found");
		}

		User user = new User();

		user.setEmail(username);
		user.setPassword(result.get(0).getPassword());

		for (UserDetailsProjection projection : result) {

			user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
		}
		return user;
	}
	
	
	protected User autheticated() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
			String username = jwtPrincipal.getClaim("username");
			User user = repository.findByEmail(username).get();
			return user;
		}
		
		catch(Exception e) {
			throw new UsernameNotFoundException("User not found");
		}
		
	}
	
	@Transactional
	public UserDTO getMe() {
		
		User user = autheticated(); // pega o user atraves do methodo de cima(autheticated())
		return new UserDTO(user); // Converte  u ser para UserDTO
	}

}
