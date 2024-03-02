package com.rehan.quizservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rehan.quizservice.entities.Quiz;
import com.rehan.quizservice.services.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizContoller {

	@Autowired
	private QuizService quizService;
	
	public QuizContoller(QuizService quizService) {
	
		this.quizService = quizService;
	}

	//create
	@PostMapping
	public Quiz create(@RequestBody Quiz quiz) {
		return quizService.add(quiz);
		
	}
	//get all
	@GetMapping
	public List<Quiz>get(){
		return quizService.get();
	}
	//get by id
	@GetMapping("/{id}")
	public Quiz getOne(@PathVariable Long id){
		return quizService.get(id);
	}
}
