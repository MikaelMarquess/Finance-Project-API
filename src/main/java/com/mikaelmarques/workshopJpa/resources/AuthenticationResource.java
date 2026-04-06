package com.mikaelmarques.workshopJpa.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mikaelmarques.workshopJpa.entities.dtos.LoginResponseDTO;
import com.mikaelmarques.workshopJpa.entities.dtos.UserLoginDTO;
import com.mikaelmarques.workshopJpa.entities.dtos.UserRegisterDTO;
import com.mikaelmarques.workshopJpa.services.AuthenticationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationResource {
	
	@Autowired
	private AuthenticationService authService;
	
	@PostMapping(value = "/register")
	public ResponseEntity<Void> registerUser(@Valid @RequestBody UserRegisterDTO userData){
		authService.registerUser(userData);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().
				path("/auth/login").build().toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<LoginResponseDTO> loginUser(@Valid @RequestBody UserLoginDTO userLogin){
		String token = authService.loginUser(userLogin);
		return ResponseEntity.ok().body(new LoginResponseDTO(token));
	}
	
}
