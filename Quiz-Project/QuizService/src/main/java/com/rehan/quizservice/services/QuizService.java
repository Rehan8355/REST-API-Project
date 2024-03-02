package com.rehan.quizservice.services;

import java.util.List;

import com.rehan.quizservice.entities.Quiz;

public interface QuizService {
	
	Quiz add(Quiz quiz);
	
	List<Quiz> get();
	
	Quiz get(Long id);

}
