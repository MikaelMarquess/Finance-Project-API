package com.mikaelmarques.workshopJpa.entities.inheritances;

import java.time.Instant;
import java.time.LocalDate;

import com.mikaelmarques.workshopJpa.entities.User;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Finances {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double financeValue;
	
	private String description;
	
	private LocalDate date;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Finances() {}

	public Finances(Long id, Double financeValue, String description, LocalDate date, User user) {
		this.id = id;
		this.financeValue = financeValue;
		this.description = description;
		this.date = date;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public Double getFinanceValue() {
		return financeValue;
	}

	public void setFinanceValue(Double revenueValue) {
		this.financeValue = revenueValue;
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

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
}
