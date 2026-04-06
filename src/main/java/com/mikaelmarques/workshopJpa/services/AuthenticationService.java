package com.mikaelmarques.workshopJpa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mikaelmarques.workshopJpa.entities.User;
import com.mikaelmarques.workshopJpa.entities.dtos.UserLoginDTO;
import com.mikaelmarques.workshopJpa.entities.dtos.UserRegisterDTO;
import com.mikaelmarques.workshopJpa.enums.UserRolesEnum;
import com.mikaelmarques.workshopJpa.repositories.UserRepository;
import com.mikaelmarques.workshopJpa.security.config.CustomUserDetails;
import com.mikaelmarques.workshopJpa.services.exceptions.ForbiddenException;
import com.mikaelmarques.workshopJpa.services.exceptions.UnauthorizedAccessException;
import com.mikaelmarques.workshopJpa.services.security.TokenService;

@Service
public class AuthenticationService{

    private final AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TokenService tokenService;

    AuthenticationService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
	
	private User fromDTO(UserRegisterDTO userAuthDto) {
		String password = new BCryptPasswordEncoder().encode(userAuthDto.getPassword());
		return new User(null, userAuthDto.getName(), userAuthDto.getEmail(), password, UserRolesEnum.USER);
	}
	
	
	protected void emailExists(String email) {
		Optional<User> user = userRepository.findByEmail(email);;
		
		if(user.isPresent()) {
			throw new UnauthorizedAccessException("This email already exists");
		}
	}
	
	private String authenticationToken(String email, String password) {
		UsernamePasswordAuthenticationToken userNamePassword = 
				new UsernamePasswordAuthenticationToken(email, password);
				
		try {
			
			Authentication auth = authenticationManager.authenticate(userNamePassword);
			
			CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
			 
			 return tokenService.generateToken(user.getUsername());
			
		} catch (AuthenticationException e) {
			throw new ForbiddenException("Incorrect email or password");
		}
	}
	
	public void registerUser(UserRegisterDTO userAuthDto) {
		User user = fromDTO(userAuthDto);
		emailExists(user.getEmail());
		userRepository.save(user);
	}

	public String loginUser(UserLoginDTO userLogin) {
		
		 return authenticationToken(userLogin.getEmail(), userLogin.getPassword());
	}
	
}
