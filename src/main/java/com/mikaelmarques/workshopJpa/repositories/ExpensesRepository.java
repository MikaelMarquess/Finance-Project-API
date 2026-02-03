package com.mikaelmarques.workshopJpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mikaelmarques.workshopJpa.entities.Expenses;

public interface ExpensesRepository extends JpaRepository<Expenses, Long>{
	
	List<Expenses> findByDescriptionContainingIgnoreCase(String description);
	
}
