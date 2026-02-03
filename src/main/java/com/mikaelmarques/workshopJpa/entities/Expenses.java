package com.mikaelmarques.workshopJpa.entities;

import java.io.Serializable;
import java.time.LocalDate;

import com.mikaelmarques.workshopJpa.entities.inheritances.Finances;
import com.mikaelmarques.workshopJpa.enums.ExpensesEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_expenses")
public class Expenses extends Finances implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Enumerated(EnumType.STRING)
	private ExpensesEnum category;
	
	public Expenses() {}
	
	public Expenses(Long id, Double dispensesValue, String description, LocalDate date, ExpensesEnum category, User user){
		super(id, dispensesValue, description, date, user);
		this.category = category;
	}

	public ExpensesEnum getCategory() {
		return category;
	}

	public void setCategory(ExpensesEnum category) {
		this.category = category;
	}
	
	
	
}
