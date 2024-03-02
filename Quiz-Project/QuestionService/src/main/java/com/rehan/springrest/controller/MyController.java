package com.rehan.springrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rehan.springrest.entities.Question;
import com.rehan.springrest.services.QuestionService;

@RestController
@RequestMapping("/question")
public class MyController {
	
	@Autowired
	private QuestionService questionService;

	public MyController(QuestionService questionService) {
		super();
		this.questionService = questionService;
	}
	
	//create a question
	@PostMapping
	public Question add(@RequestBody Question question) {
		return this.questionService.add(question);
	}
	
	//GET the list
	@GetMapping
	public List<Question> get( ){
		return this.questionService.get();
	}
	//Get by id
	@GetMapping("/{questionId}")
	public Question getOne(@PathVariable Long questionId) {
		return this.getOne(questionId);
		
	}
	@GetMapping({"/quiz/{quizId}"})
	public List<Question>getQuestionsOfQuiz(@PathVariable Long quizId){
		return questionService.getQuestionsOfQuiz(quizId);
		
	}

}
