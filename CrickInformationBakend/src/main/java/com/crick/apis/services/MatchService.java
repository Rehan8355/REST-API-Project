package com.crick.apis.services;

import java.util.List;
import java.util.Map;

import com.crick.apis.entities.Match;

public interface MatchService {
	//get all
	
	


	List<Match>getAllMatches();
	
	//get live matches
	List<Match> getLiveMatchs();
	
	
	//get 2024 point table
	List<List<String>> getPointTable();

	

}
