package com.mvc.overflow.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mvc.overflow.models.Tags;

@Repository
public interface TagRepository extends CrudRepository<Tags,Long>{
	List<Tags> findAll();
	
	List<Tags> findBySubjectContaining(String s);
}
