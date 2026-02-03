package com.mikaelmarques.workshopJpa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mikaelmarques.workshopJpa.entities.User;
import com.mikaelmarques.workshopJpa.entities.dtos.UserLoginDTO;
import com.mikaelmarques.workshopJpa.entities.dtos.UserRegisterDTO;
import com.mikaelmarques.workshopJpa.repositories.UserRepository;
import com.mikaelmarques.workshopJpa.services.exceptions.UnauthorizedAccessException;

@Service
public class AuthenticationService {
	
	@Autowired
	private UserRepository userRepository;
	
	private User fromDTO(UserRegisterDTO userAuthDto) {
		return new User(null, userAuthDto.getName(), userAuthDto.getEmail(), userAuthDto.getPassword());
	}
	
	protected User emailVerification(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		return user.orElseThrow(() -> new UnauthorizedAccessException("Incorrect email or password"));
	}
	
	protected User emailExists(String email) {
		Optional<User> user = userRepository.findByEmail(email);;
		
		if(user.isPresent()) {
			throw new UnauthorizedAccessException("This email already exists");
		}
		
		return user.orElse(null);
	}
	
	private void authenticateUserPassword(String password, String userPassword) {
		if(!password.equals(userPassword)) {
			throw new UnauthorizedAccessException("Incorrect email or password");
		}
	}
	
	public void registerUser(UserRegisterDTO userAuthDto) {
		User user = fromDTO(userAuthDto);
		emailExists(user.getEmail());
		userRepository.save(user);
	}

	public User loginUser(UserLoginDTO userLogin) {
		User user = emailVerification(userLogin.getEmail());
		authenticateUserPassword(user.getPassword(), userLogin.getPassword());
		return user;
	}
	
}
