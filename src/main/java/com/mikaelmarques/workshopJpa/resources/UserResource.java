package com.mikaelmarques.workshopJpa.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mikaelmarques.workshopJpa.entities.dtos.AllFinancesDTO;
import com.mikaelmarques.workshopJpa.entities.dtos.UserAuthenticationDTO;
import com.mikaelmarques.workshopJpa.entities.dtos.UserDeleteDTO;
import com.mikaelmarques.workshopJpa.entities.dtos.UserUpdateDTO;
import com.mikaelmarques.workshopJpa.services.UserFinanceService;
import com.mikaelmarques.workshopJpa.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserFinanceService userFinanceService;
	
	@GetMapping(value = "/{authorId}/finances")
	public ResponseEntity<AllFinancesDTO> findAllFinances(@PathVariable Long authorId){
		AllFinancesDTO financesUser = userFinanceService.findFinances(authorId);
		return ResponseEntity.ok().body(financesUser);
	}
	
	@PutMapping(value = "/{authorId}/updateData")
	public ResponseEntity<UserAuthenticationDTO> updateUserData(@PathVariable Long authorId, @RequestBody UserUpdateDTO userDto){
		userService.userUpdateData(authorId, userDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().
				path("/authentication/login").buildAndExpand().toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value = "/{id}/delete")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id, @RequestBody UserDeleteDTO user){
		userService.deleteUser(id, user);
		return ResponseEntity.noContent().build();
	}
	
}
