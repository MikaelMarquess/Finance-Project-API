package com.mikaelmarques.workshopJpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mikaelmarques.workshopJpa.entities.Expenses;
import com.mikaelmarques.workshopJpa.entities.User;
import com.mikaelmarques.workshopJpa.entities.dtos.ExpensesDTO;
import com.mikaelmarques.workshopJpa.repositories.ExpensesRepository;
import com.mikaelmarques.workshopJpa.repositories.UserRepository;
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
	
	private Expenses convertFromDTO(ExpensesDTO revenueDto, User user) {
		return new Expenses(null, revenueDto.getFinanceValue(), revenueDto.getDescription(),
				revenueDto.getDate(),revenueDto.getCategory(), user);
	}
	
	private void financeThisUser(Long authorId, Long AuthorExpensesId) {
		if(!authorId.equals(AuthorExpensesId)) {
			throw new ForbiddenException();
		}
	}
	
	private void updateDataExpense(Expenses expenses, ExpensesDTO expensesData) {
		financeThisUser(expenses.getUser().getId(), expensesData.getUser().getId());
		expenses.setFinanceValue(expensesData.getFinanceValue());
		expenses.setDescription(expensesData.getDescription());
		expenses.setDate(expensesData.getDate());
		expenses.setCategory(expensesData.getCategory());
	}
	
	public Expenses createExpense(Long authorId, ExpensesDTO expensesDto) {
		User user = userService.findUserById(authorId);
		financeThisUser(authorId, expensesDto.getUser().getId());
		Expenses expense = convertFromDTO(expensesDto, user);
		expenseRepository.save(expense);
		userRepository.save(user);
		return expense;
	}
	
	public Expenses updateExpense(Long expenseId, ExpensesDTO expenseData) {
		Expenses expense = findExpenseById(expenseId);
		updateDataExpense(expense, expenseData);
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
