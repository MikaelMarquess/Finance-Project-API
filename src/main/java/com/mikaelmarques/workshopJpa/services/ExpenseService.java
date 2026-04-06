package com.mikaelmarques.workshopJpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mikaelmarques.workshopJpa.entities.Expenses;
import com.mikaelmarques.workshopJpa.entities.User;
import com.mikaelmarques.workshopJpa.entities.dtos.CreateExpensesDTO;
import com.mikaelmarques.workshopJpa.entities.dtos.ExpensesDTO;
import com.mikaelmarques.workshopJpa.entities.dtos.UpdateExpensesDTO;
import com.mikaelmarques.workshopJpa.repositories.ExpensesRepository;
import com.mikaelmarques.workshopJpa.repositories.UserRepository;
import com.mikaelmarques.workshopJpa.security.config.CustomUserDetails;
import com.mikaelmarques.workshopJpa.services.exceptions.ForbiddenException;
import com.mikaelmarques.workshopJpa.services.exceptions.ResourceNotFound;

@Service
public class ExpenseService {
	
	@Autowired
	private ExpensesRepository expenseRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	public Expenses findExpenseById(Long id) {
		Optional<Expenses> expenses = expenseRepository.findById(id);
		return expenses.orElseThrow(() -> new ResourceNotFound());
	}
	
	protected List<Expenses> findExpenseByDescription(String description) {
		List<Expenses> expenses = expenseRepository.findByDescriptionContainingIgnoreCase(description);
		return expenses;
	}
	
	private Expenses convertFromDTO(CreateExpensesDTO revenueDto, User user) {
		return new Expenses(null, revenueDto.getFinanceValue(), revenueDto.getDescription(),
				revenueDto.getDate(),revenueDto.getCategory(), user);
	}
	
	private void financeThisUser(Long authorId, Long AuthorExpensesId) {
		if(!authorId.equals(AuthorExpensesId)) {
			throw new ForbiddenException("Unauthorized request.");
		}
	}
	
	private void updateDataExpense(Expenses expenses, Long authorExpenseId ,UpdateExpensesDTO expensesData) {
		financeThisUser(expenses.getUser().getId(), authorExpenseId);
		expenses.setFinanceValue(expensesData.getFinanceValue());
		expenses.setDescription(expensesData.getDescription());
		expenses.setDate(expensesData.getDate());
		expenses.setCategory(expensesData.getCategory());
	}
	
	public Expenses createExpense(Long authorId, CreateExpensesDTO expensesDto) {
		User user = userService.findUserById(authorId);
		Expenses expense = convertFromDTO(expensesDto, user);
		expenseRepository.save(expense);
		return expense;
	}
	
	public Expenses updateExpense(Long expenseId, Long userId, UpdateExpensesDTO expenseData) {
		Expenses expense = findExpenseById(expenseId);
		financeThisUser(userId, expense.getUser().getId());
		updateDataExpense(expense, userId ,expenseData);
		expenseRepository.save(expense);
		return expense;
	}
	
	public void deleteRevenue(Long authorId, Long expenseId) {
		userService.findUserById(authorId);
		
		Expenses expense = findExpenseById(expenseId);
		
		financeThisUser(authorId, expense.getUser().getId());
		expenseRepository.delete(expense);
	}
}
