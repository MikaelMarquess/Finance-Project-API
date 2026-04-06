package com.mikaelmarques.workshopJpa.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mikaelmarques.workshopJpa.entities.Expenses;
import com.mikaelmarques.workshopJpa.entities.dtos.CreateExpensesDTO;
import com.mikaelmarques.workshopJpa.entities.dtos.ExpensesDTO;
import com.mikaelmarques.workshopJpa.entities.dtos.UpdateExpensesDTO;
import com.mikaelmarques.workshopJpa.security.config.CustomUserDetails;
import com.mikaelmarques.workshopJpa.services.ExpenseService;

@RestController
@RequestMapping(value = "/expenses")
public class ExpenseResource {
	
	@Autowired
	private ExpenseService expenseService;
	
	@PostMapping(value = "/create")
	public ResponseEntity<ExpensesDTO> createExpense(@AuthenticationPrincipal CustomUserDetails token, @RequestBody CreateExpensesDTO expenseDto) {
		Expenses expenses = expenseService.createExpense(token.getUserId(), expenseDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().
				path("/users/finances").build().toUri();
		return ResponseEntity.created(uri).body(new ExpensesDTO(expenses));
	}
	
	@PutMapping(value = "/update/{expenseId}")
	public ResponseEntity<ExpensesDTO> updateRevenue(@PathVariable Long expenseId, @AuthenticationPrincipal CustomUserDetails token,  @RequestBody UpdateExpensesDTO expenseData){
		Expenses revenueUpdated = expenseService.updateExpense(expenseId, token.getUserId() ,expenseData);
		return ResponseEntity.ok().body(new ExpensesDTO(revenueUpdated));
	}
	
	@DeleteMapping(value = "/delete/{expenseId}")
	public ResponseEntity<Void> deleteRevenue(@AuthenticationPrincipal CustomUserDetails token, @PathVariable Long expenseId){
		expenseService.deleteRevenue(token.getUserId(), expenseId);
		return ResponseEntity.ok().build();
	}
	
}
