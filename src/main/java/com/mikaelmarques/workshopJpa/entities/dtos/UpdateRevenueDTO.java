package com.mikaelmarques.workshopJpa.entities.dtos;

import java.time.LocalDate;

import com.mikaelmarques.workshopJpa.entities.Revenue;
import com.mikaelmarques.workshopJpa.enums.RevenueEnum;

public class UpdateRevenueDTO {

	private Double financeValue;

	private String description;

	private LocalDate date;
	
	private RevenueEnum category;
	
	public UpdateRevenueDTO() {}
	
	public UpdateRevenueDTO(Revenue revenue) {
		 this.financeValue = revenue.getFinanceValue();
		 this.description = revenue.getDescription();
		 this.date = revenue.getDate();
		 this.category = revenue.getCategory();
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

	
}
