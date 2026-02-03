package com.mikaelmarques.workshopJpa.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mikaelmarques.workshopJpa.entities.Revenue;
import com.mikaelmarques.workshopJpa.entities.dtos.RevenueDTO;
import com.mikaelmarques.workshopJpa.services.RevenueService;

@RestController
@RequestMapping(value = "/revenues")
public class RevenueResource {
	
	@Autowired
	private RevenueService revenueService;
	
	@PostMapping(value = "/{authorId}")
	public ResponseEntity<RevenueDTO> createRevenue(@PathVariable Long authorId, @RequestBody RevenueDTO revenueDto) {
		Revenue revenue = revenueService.createRevenue(authorId, revenueDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().
				path("/users/{id}/finances").buildAndExpand(revenue.getUser().getId()).toUri();
		return ResponseEntity.created(uri).body(new RevenueDTO(revenue));
	}
	
	@PutMapping(value = "/update/{revenueId}")
	public ResponseEntity<RevenueDTO> updateRevenue(@PathVariable Long revenueId, @RequestBody RevenueDTO revenueData){
		Revenue revenueUpdated = revenueService.updateRevenue(revenueId, revenueData);
		return ResponseEntity.ok().body(new RevenueDTO(revenueUpdated));
	}
	
	@DeleteMapping(value = "/{authorId}/delete/{revenueId}")
	public ResponseEntity<Void> deleteRevenue(@PathVariable Long authorId, @PathVariable Long revenueId){
		revenueService.deleteRevenue(authorId, revenueId);
		return ResponseEntity.ok().build();
	}
	
}
