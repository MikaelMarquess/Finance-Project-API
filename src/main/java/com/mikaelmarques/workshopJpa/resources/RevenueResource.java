package com.mikaelmarques.workshopJpa.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mikaelmarques.workshopJpa.entities.Revenue;
import com.mikaelmarques.workshopJpa.entities.dtos.CreateRevenueDTO;
import com.mikaelmarques.workshopJpa.entities.dtos.RevenueDTO;
import com.mikaelmarques.workshopJpa.entities.dtos.UpdateRevenueDTO;
import com.mikaelmarques.workshopJpa.security.config.CustomUserDetails;
import com.mikaelmarques.workshopJpa.services.RevenueService;

@RestController
@RequestMapping(value = "/revenues")
public class RevenueResource {
	
	@Autowired
	private RevenueService revenueService;
	
	@PostMapping(value = "/create")
	public ResponseEntity<RevenueDTO> createRevenue(@AuthenticationPrincipal CustomUserDetails token, @RequestBody CreateRevenueDTO revenueDto) {
		Revenue revenue = revenueService.createRevenue(token.getUserId(), revenueDto);
		return ResponseEntity.ok().body(new RevenueDTO(revenue));
	}
	
	@PutMapping(value = "/update/{revenueId}")
	public ResponseEntity<RevenueDTO> updateRevenue(@AuthenticationPrincipal CustomUserDetails token, @PathVariable Long revenueId, @RequestBody UpdateRevenueDTO revenueData){
		Revenue revenueUpdated = revenueService.updateRevenue(token.getUserId(), revenueId, revenueData);
		return ResponseEntity.ok().body(new RevenueDTO(revenueUpdated));
	}
	
	@DeleteMapping(value = "/delete/{revenueId}")
	public ResponseEntity<Void> deleteRevenue(@AuthenticationPrincipal CustomUserDetails token, @PathVariable Long revenueId){
		revenueService.deleteRevenue(token.getUserId(), revenueId);
		return ResponseEntity.ok().build();
	}
	
}
