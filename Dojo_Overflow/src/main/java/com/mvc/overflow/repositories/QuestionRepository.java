package com.mvc.overflow.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mvc.overflow.models.Questions;

@Repository
public interface QuestionRepository extends CrudRepository<Questions,Long>{
	List<Questions> findAll();
}
