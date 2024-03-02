package com.rehan.quizservice.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rehan.quizservice.entities.Quiz;
import com.rehan.quizservice.repository.QuizRepository;

@Service
public class QuizServiceImp implements QuizService {

    @Autowired
    private QuizRepository quizRepository;
    
    private QuistionClient questionClient;
    

    //constructor

    public QuizServiceImp(QuizRepository quizRepository, QuistionClient questionClient) {
		
		this.quizRepository = quizRepository;
		this.questionClient = questionClient;
	}

  //method implementation
    @Override
    public Quiz add(Quiz quiz) {
        return quizRepository.save(quiz);
    }

	@Override
    public List<Quiz> get() {
		List<Quiz>quizzes=quizRepository.findAll();
		List<Quiz> newQuizList=quizzes.stream().map(quiz->{
			quiz.setQuestions(questionClient.getQuestionsOfQuiz(quiz.getId()));
			return quiz;
		}).collect(Collectors.toList());
        return newQuizList;
    }

    @Override
    public Quiz get(Long id) {
        Quiz quiz= quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + id));
        quiz.setQuestions(questionClient.getQuestionsOfQuiz(quiz.getId()));
        return quiz;
    }
}