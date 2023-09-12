package com.mvc.overflow.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mvc.overflow.models.Answers;

@Repository
public interface AnswerRepository extends CrudRepository<Answers,Long>{
	List<Answers> findAll();
	
	Answers findByAnswer(String s);
}
