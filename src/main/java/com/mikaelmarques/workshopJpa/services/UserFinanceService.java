package com.mikaelmarques.workshopJpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mikaelmarques.workshopJpa.entities.Expenses;
import com.mikaelmarques.workshopJpa.entities.Revenue;
import com.mikaelmarques.workshopJpa.entities.User;
import com.mikaelmarques.workshopJpa.entities.dtos.AllFinancesDTO;
import com.mikaelmarques.workshopJpa.entities.dtos.ExpensesDTO;
import com.mikaelmarques.workshopJpa.entities.dtos.RevenueDTO;

@Service
public class UserFinanceService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ExpenseService expenseService;
	
	@Autowired
	private RevenueService revenueService;
	
	private AllFinancesDTO createAllFinanceDto(Long authorId, List<Revenue> revenues, List<Expenses> expenses) {
		
		AllFinancesDTO newFinanceDto = new AllFinancesDTO();
		newFinanceDto.setAuthorId(authorId);
		newFinanceDto.setRevenues(revenues.stream().map((x) -> new RevenueDTO(x)).toList());
		newFinanceDto.setExpenses(expenses.stream().map((x) -> new ExpensesDTO(x)).toList());
		return newFinanceDto;
	}
	
	private AllFinancesDTO totalFinanceCalculation(AllFinancesDTO finances) {
		double revenue = 0.0;
		double expense = 0.0;
		for (RevenueDTO totalRevenue : finances.getRevenues()) {
			revenue += totalRevenue.getFinanceValue();
		}

		finances.setTotalRevenue(revenue);

		for (ExpensesDTO totalExpenses : finances.getExpenses()) {
			expense += totalExpenses.getFinanceValue();
		}

		finances.setTotalDispense(expense);

		double totalCalculation = revenue - expense;

		finances.setFinalRecipe(totalCalculation);
		return finances;
	}

	public AllFinancesDTO findFinances(Long id) {
		User user = userService.findUserById(id);
		return totalFinanceCalculation(new AllFinancesDTO(user));
	}
	
}
