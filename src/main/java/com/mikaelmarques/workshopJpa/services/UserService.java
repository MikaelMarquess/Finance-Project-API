package com.mikaelmarques.workshopJpa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mikaelmarques.workshopJpa.entities.User;
import com.mikaelmarques.workshopJpa.entities.dtos.UserDeleteDTO;
import com.mikaelmarques.workshopJpa.entities.dtos.UserUpdateDTO;
import com.mikaelmarques.workshopJpa.repositories.UserRepository;
import com.mikaelmarques.workshopJpa.services.exceptions.ResourceNotFound;
import com.mikaelmarques.workshopJpa.services.exceptions.UnauthorizedAccessException;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
	
    UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
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
	
	private void verificationCredentials(User user, String email, String password) {
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
		boolean correctPassword = encode.matches(password, user.getPassword());
		if(!user.getEmail().equals(email) || !correctPassword) {
			throw new UnauthorizedAccessException("Incorrect email or password.");
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
			String hashPassword = passwordEncoder.encode(userNewData.getPassword());
			user.setPassword(hashPassword);
		}
		return user;
	}
	
	public void userUpdateData(Long authorId, UserUpdateDTO userAuthDto) {
		User user = findUserById(authorId);
		User userUpdated = updateUser(user, userAuthDto);
		userRepository.save(userUpdated);
	}
	
	public void deleteUser(Long id, UserDeleteDTO userData) {
		User user = findById(id);
		verificationCredentials(user, userData.getEmail(), userData.getPassword());
		userRepository.deleteById(id);
	}
	
}
