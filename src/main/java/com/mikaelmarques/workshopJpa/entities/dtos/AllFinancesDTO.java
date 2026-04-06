package com.mikaelmarques.workshopJpa.entities.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mikaelmarques.workshopJpa.entities.User;

@JsonPropertyOrder({
    "authorId",
    "revenues",
    "expenses",
    "totalRevenue",
    "totalDispense",
    "finalRecipe"
})

public class AllFinancesDTO {
	
	private Double finalRecipe;
	
	private Double totalRevenue;
	
	private Double totalExpense;
	
	private List<RevenueDTO> revenues = new ArrayList<RevenueDTO>();
	
	private List<ExpensesDTO> expenses = new ArrayList<ExpensesDTO>();
	
	public AllFinancesDTO() {}
	
	public AllFinancesDTO(User user) {
		this.revenues = user.getRevenues().stream().map((x) -> new RevenueDTO(x)).collect(Collectors.toList());
		this.expenses = user.getExpenses().stream().map((x) -> new ExpensesDTO(x)).collect(Collectors.toList());
	}

	public List<RevenueDTO> getRevenues() {
		return revenues;
	}
	
	public void setRevenues(List<RevenueDTO> revenues) {
		 this.revenues = revenues;
	}

	public List<ExpensesDTO> getExpenses() {
		return expenses;
	}
	
	public void setExpenses(List<ExpensesDTO> expenses) {
		 this.expenses = expenses;
	}
	
	public void setFinalRecipe(Double totalFinance) {
		this.finalRecipe = totalFinance;
	}

	public Double getFinalRecipe() {
		return finalRecipe;
	}

	public Double getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(Double totalExpense) {
		this.totalRevenue = totalExpense;
	}

	public Double getTotalDispense() {
		return totalExpense;
	}

	public void setTotalDispense(Double totalDispense) {
		this.totalExpense = totalDispense;
	}

	
	
	
	
}
