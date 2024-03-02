package com.rehan.springrest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rehan.springrest.entities.Question;

public interface QuestionRepository extends JpaRepository<Question,Long > {
	
	//custom finder method
	List<Question> findByQuizId(Long quizId);

}
