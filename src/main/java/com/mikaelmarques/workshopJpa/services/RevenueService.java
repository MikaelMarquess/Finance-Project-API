package com.mikaelmarques.workshopJpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mikaelmarques.workshopJpa.entities.Revenue;
import com.mikaelmarques.workshopJpa.entities.User;
import com.mikaelmarques.workshopJpa.entities.dtos.CreateRevenueDTO;
import com.mikaelmarques.workshopJpa.entities.dtos.RevenueDTO;
import com.mikaelmarques.workshopJpa.entities.dtos.UpdateRevenueDTO;
import com.mikaelmarques.workshopJpa.repositories.RevenueRepository;
import com.mikaelmarques.workshopJpa.services.exceptions.ForbiddenException;
import com.mikaelmarques.workshopJpa.services.exceptions.ResourceNotFound;

@Service
public class RevenueService {
	
	@Autowired
	private RevenueRepository revenueRepository;
	
	@Autowired
	private UserService userService;
	
	public Revenue findRevenueById(Long id) {
		Optional<Revenue> revenue = revenueRepository.findById(id);
		return revenue.orElseThrow(() -> new ResourceNotFound());
	}
	
	protected List<Revenue> findRevenueByDescription(String description) {
		List<Revenue> revenue = revenueRepository.findByDescriptionContainingIgnoreCase(description);
		return revenue;
	}
	
	private Revenue convertFromDTO(CreateRevenueDTO revenueDto, User user) {
		return new Revenue(null, revenueDto.getFinanceValue(), revenueDto.getDescription(),
				revenueDto.getDate(),revenueDto.getCategory(), user);
	}
	
	private void financeThisUser(Long authorId, Long authorRevenueId) {
		if(!authorId.equals(authorRevenueId)) {
			throw new ForbiddenException("unauthorized request.");
		}
	}
	
	private void updateDataRevenue(Revenue revenue, UpdateRevenueDTO revenueData) {
		revenue.setFinanceValue(revenueData.getFinanceValue());
		revenue.setDescription(revenueData.getDescription());
		revenue.setDate(revenueData.getDate());
		revenue.setCategory(revenueData.getCategory());
	}
	
	public Revenue createRevenue(Long authorId, CreateRevenueDTO revenueDto) {
		User user = userService.findUserById(authorId);
		Revenue revenue = convertFromDTO(revenueDto, user);
		revenueRepository.save(revenue);
		return revenue;
	}
	
	public Revenue updateRevenue(Long authorId, Long revenueId, UpdateRevenueDTO revenueData) {
		Revenue revenue = findRevenueById(revenueId);
		financeThisUser(revenue.getUser().getId(), authorId);
		updateDataRevenue(revenue, revenueData);
		revenueRepository.save(revenue);
		return revenue;
	}
	
	public void deleteRevenue(Long authorId, Long revenueId) {
		Revenue revenue = findRevenueById(revenueId);
		
		financeThisUser(authorId, revenue.getUser().getId());
		revenueRepository.delete(revenue);
	}
}
