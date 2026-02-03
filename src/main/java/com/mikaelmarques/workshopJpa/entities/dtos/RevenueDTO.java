package com.mikaelmarques.workshopJpa.entities.dtos;

import java.time.Instant;
import java.time.LocalDate;

import com.mikaelmarques.workshopJpa.entities.Revenue;
import com.mikaelmarques.workshopJpa.enums.RevenueEnum;

public class RevenueDTO {

	private Long id;

	private Double financeValue;

	private String description;

	private LocalDate date;
	
	private RevenueEnum category;

	private UserDTO user;
	
	public RevenueDTO() {}
	
	public RevenueDTO(Revenue revenue) {
		 this.id = revenue.getId();
		 this.financeValue = revenue.getFinanceValue();
		 this.description = revenue.getDescription();
		 this.date = revenue.getDate();
		 this.category = revenue.getCategory();
		 this.user = new UserDTO(revenue.getUser());
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

	public RevenueEnum getCategory() {
		return category;
	}

	public void setCategory(RevenueEnum category) {
		this.category = category;
	}
	
	
	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	
}
