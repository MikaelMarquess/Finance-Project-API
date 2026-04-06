package com.mikaelmarques.workshopJpa.entities.dtos;

import java.time.LocalDate;

import com.mikaelmarques.workshopJpa.entities.Expenses;
import com.mikaelmarques.workshopJpa.enums.ExpensesEnum;

public class UpdateExpensesDTO {

	private Double financeValue;

	private String description;
	
	private LocalDate date;
	
	private ExpensesEnum category;
	
	public UpdateExpensesDTO() {}
	
	public UpdateExpensesDTO(Expenses expenses) {
		 this.financeValue = expenses.getFinanceValue();
		 this.description = expenses.getDescription();
		 this.date = expenses.getDate();
		 this.category = expenses.getCategory();
	}

	public Double getFinanceValue() {
		return financeValue;
	}

	public void setFinanceValue(Double financeValue) {
		this.financeValue = financeValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public ExpensesEnum getCategory() {
		return category;
	}

	public void setCategory(ExpensesEnum category) {
		this.category = category;
	}
	
}
