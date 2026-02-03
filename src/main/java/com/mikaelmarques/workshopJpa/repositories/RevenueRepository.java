package com.mikaelmarques.workshopJpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mikaelmarques.workshopJpa.entities.Revenue;

public interface RevenueRepository extends JpaRepository<Revenue, Long>{
	
	 List<Revenue> findByDescriptionContainingIgnoreCase(String description);
	
}
