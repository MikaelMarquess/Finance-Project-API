package com.mikaelmarques.workshopJpa.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mikaelmarques.workshopJpa.entities.dtos.AllFinancesDTO;
import com.mikaelmarques.workshopJpa.entities.dtos.UserAuthenticationDTO;
import com.mikaelmarques.workshopJpa.entities.dtos.UserDeleteDTO;
import com.mikaelmarques.workshopJpa.entities.dtos.UserUpdateDTO;
import com.mikaelmarques.workshopJpa.security.config.CustomUserDetails;
import com.mikaelmarques.workshopJpa.services.UserFinanceService;
import com.mikaelmarques.workshopJpa.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserFinanceService userFinanceService;
	
	@GetMapping(value = "/finances")
	public ResponseEntity<AllFinancesDTO> findAllFinances(@AuthenticationPrincipal CustomUserDetails token){
		AllFinancesDTO financesUser = userFinanceService.findFinances(token.getUserId());
		return ResponseEntity.ok().body(financesUser);
	}
	
	@PutMapping(value = "/updateData")
	public ResponseEntity<UserAuthenticationDTO> updateUserData(@AuthenticationPrincipal CustomUserDetails token, @RequestBody UserUpdateDTO userDto){
		userService.userUpdateData(token.getUserId(), userDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().
				path("/authentication/login").buildAndExpand().toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value = "/delete")
	public ResponseEntity<Void> deleteUser(@AuthenticationPrincipal CustomUserDetails token, @Valid @RequestBody UserDeleteDTO user){
		userService.deleteUser(token.getUserId(), user);
		return ResponseEntity.noContent().build();
	}
	
}
