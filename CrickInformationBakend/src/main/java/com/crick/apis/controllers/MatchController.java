package com.crick.apis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crick.apis.entities.Match;
import com.crick.apis.services.MatchService;

@RestController
@RequestMapping("/match")
public class MatchController {
   
	@Autowired
	private MatchService matchService;
	
	public MatchController(MatchService matchService) {
		
		this.matchService = matchService;
	}


	// Get all live matches
	@GetMapping("/live")
	public ResponseEntity<List<Match>> getLiveMatches() {
	    List<Match> matches = this.matchService.getLiveMatchs(); // Call the method getAllMatches()
	    return new ResponseEntity<>(matches, HttpStatus.OK);
	}
	
	// Get all  matches
		@GetMapping
		public ResponseEntity<List<Match>> getAllMatches() {
		    List<Match> matches = this.matchService.getAllMatches(); // Call the method getAllMatches()
		    return new ResponseEntity<>(matches, HttpStatus.OK);
		}
		
		// get point table
		
		@GetMapping("/point-table")
		public ResponseEntity<?>getPointTable(){
			return new ResponseEntity<>(this.matchService.getPointTable(),HttpStatus.OK);
			
		}
}
