package com.rehan.quizservice.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rehan.quizservice.entities.Question;

//@FeignClient(url="http://localhost:8082",value="Question-Client")
@FeignClient(name="QUESTION-SERVICE",value="Question-Client")
public interface QuistionClient {

	@GetMapping("/question/quiz/{quizId}")
	List<Question>getQuestionsOfQuiz(@PathVariable Long quizId);
	
}
