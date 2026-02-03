package com.mikaelmarques.workshopJpa.entities.dtos;

import java.time.LocalDate;

import com.mikaelmarques.workshopJpa.entities.Expenses;
import com.mikaelmarques.workshopJpa.enums.ExpensesEnum;

public class ExpensesDTO {
	
	private Long id;

	private Double financeValue;

	private String description;
	
	private LocalDate date;
	
	private ExpensesEnum category;

	private UserDTO user;
	
	public ExpensesDTO() {}
	
	public ExpensesDTO(Expenses expenses) {
		 this.id = expenses.getId();
		 this.financeValue = expenses.getFinanceValue();
		 this.description = expenses.getDescription();
		 this.date = expenses.getDate();
		 this.category = expenses.getCategory();
		 this.user = new UserDTO(expenses.getUser());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	
}
