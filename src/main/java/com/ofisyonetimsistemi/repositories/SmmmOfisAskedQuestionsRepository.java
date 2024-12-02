package com.ofisyonetimsistemi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ofisyonetimsistemi.models.SmmmOfisAskedQuestions;

@Repository
public interface SmmmOfisAskedQuestionsRepository extends JpaRepository<SmmmOfisAskedQuestions, Integer>{

}
