package com.rehan.springrest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rehan.springrest.entities.Question;
import com.rehan.springrest.repository.QuestionRepository;
 
@Service
public class QuestionServiceImp implements QuestionService {
	 @Autowired
	 public QuestionRepository questionRepository;
	 
	 public QuestionServiceImp(QuestionRepository questionRepository) {
		super();
		this.questionRepository = questionRepository;
	}

	@Override
	public Question add(Question question) {
		
		return questionRepository.save(question);
	}

	@Override
	public List<Question> get() {
		
		return questionRepository.findAll();
	}

	@Override
	public Question get(Long questionId) {
		
		return questionRepository.findById(questionId)
	            .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + questionId));
	}

	@Override
	public List<Question> getQuestionsOfQuiz(Long quizId) {
		
		return questionRepository.findByQuizId(quizId);
	}

}
