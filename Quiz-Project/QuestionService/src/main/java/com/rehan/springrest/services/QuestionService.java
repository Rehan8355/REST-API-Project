package com.rehan.springrest.services;

import java.util.List;

import com.rehan.springrest.entities.Question;


public interface QuestionService {

    Question add(Question question);
	
	List<Question> get();
	
	Question get(Long questionId);
	
	
	List<Question>getQuestionsOfQuiz(Long quizId);
}
