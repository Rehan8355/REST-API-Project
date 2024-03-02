package com.rehan.quizservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rehan.quizservice.entities.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

}
