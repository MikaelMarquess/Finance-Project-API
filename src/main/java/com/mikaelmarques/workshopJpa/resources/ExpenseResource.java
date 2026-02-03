package com.mikaelmarques.workshopJpa.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mikaelmarques.workshopJpa.entities.Expenses;
import com.mikaelmarques.workshopJpa.entities.dtos.ExpensesDTO;
import com.mikaelmarques.workshopJpa.services.ExpenseService;

@RestController
@RequestMapping(value = "/expenses")
public class ExpenseResource {
	
	@Autowired
	private ExpenseService expenseService;
	
	@PostMapping(value = "/{authorId}")
	public ResponseEntity<ExpensesDTO> createRevenue(@PathVariable Long authorId, @RequestBody ExpensesDTO expenseDto) {
		Expenses expenses = expenseService.createExpense(authorId, expenseDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().
				path("/users/{id}/finances").buildAndExpand(expenses.getUser().getId()).toUri();
		return ResponseEntity.created(uri).body(new ExpensesDTO(expenses));
	}
	
	@PutMapping(value = "/update/{expenseId}")
	public ResponseEntity<ExpensesDTO> updateRevenue(@PathVariable Long expenseId, @RequestBody ExpensesDTO expenseData){
		Expenses revenueUpdated = expenseService.updateExpense(expenseId, expenseData);
		return ResponseEntity.ok().body(new ExpensesDTO(revenueUpdated));
	}
	
	@DeleteMapping(value = "/{authorId}/delete/{expenseId}")
	public ResponseEntity<Void> deleteRevenue(@PathVariable Long authorId, @PathVariable Long expenseId){
		expenseService.deleteRevenue(authorId, expenseId);
		return ResponseEntity.ok().build();
	}
	
}
