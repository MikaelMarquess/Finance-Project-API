package com.mikaelmarques.workshopJpa.entities;

import java.io.Serializable;
import java.time.LocalDate;

import com.mikaelmarques.workshopJpa.entities.inheritances.Finances;
import com.mikaelmarques.workshopJpa.enums.RevenueEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_revenue")
public class Revenue extends Finances implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Enumerated(EnumType.STRING)
	private RevenueEnum category;
	
	public Revenue() {}

	public Revenue(Long id, Double revenueValue, String description, LocalDate date, RevenueEnum category, User user) {
		super(id, revenueValue, description, date, user);
		this.category = category;
	}

	public RevenueEnum getCategory() {
		return category;
	}

	public void setCategory(RevenueEnum category) {
		this.category = category;
	}
	
	
	
}
