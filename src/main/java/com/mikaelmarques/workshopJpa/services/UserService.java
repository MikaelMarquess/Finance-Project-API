package com.mikaelmarques.workshopJpa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mikaelmarques.workshopJpa.entities.User;
import com.mikaelmarques.workshopJpa.entities.dtos.UserDeleteDTO;
import com.mikaelmarques.workshopJpa.entities.dtos.UserUpdateDTO;
import com.mikaelmarques.workshopJpa.repositories.UserRepository;
import com.mikaelmarques.workshopJpa.services.exceptions.ForbiddenException;
import com.mikaelmarques.workshopJpa.services.exceptions.ResourceNotFound;
import com.mikaelmarques.workshopJpa.services.exceptions.UnauthorizedAccessException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	private User findById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new ResourceNotFound());
	}
	
	protected User findUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new ResourceNotFound());
	}
	
	private void userAuthentication(User user, UserUpdateDTO userDto) {
		if(!user.getId().equals(userDto.getId())) {
			throw new ForbiddenException();
		}
	}
	
	private void verificationCredentials(User user, String email, String password) {
		if(!user.getEmail().equals(email) || !user.getPassword().equals(password)) {
			throw new UnauthorizedAccessException("Email ou senha incorretas");
		}
	}
	
	private User updateUser(User user, UserUpdateDTO userNewData) {
		if(userNewData.getName() != null) {
			user.setName(userNewData.getName());
		}
		authenticationService.emailExists(userNewData.getEmail());
		if(userNewData.getEmail() != null) {
			user.setEmail(userNewData.getEmail());
		}
		if(userNewData.getPassword() != null) {
			user.setPassword(userNewData.getPassword());
		}
		return user;
	}
	
	public void userUpdateData(Long authorId, UserUpdateDTO userAuthDto) {
		User user = findUserById(authorId);
		userAuthentication(user, userAuthDto);
		User userUpdated = updateUser(user, userAuthDto);
		userRepository.save(userUpdated);
	}
	
	public void deleteUser(Long id, UserDeleteDTO userData) {
		User user = findById(id);
		verificationCredentials(user, userData.getEmail(), userData.getPassword());
		userRepository.deleteById(id);
	}
	
}
