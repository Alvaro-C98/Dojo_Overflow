package com.mvc.overflow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mvc.overflow.models.Questions;
import com.mvc.overflow.models.Tags;
import com.mvc.overflow.repositories.QuestionRepository;
import com.mvc.overflow.repositories.TagRepository;

@Service
public class MainService {
	private final QuestionRepository queRep;
	private final TagRepository tagRep;

	public MainService(QuestionRepository q, TagRepository t) {
		this.queRep=q;
		this.tagRep=t;
	}
	
	public Questions createQuestion(Questions q) {
		return queRep.save(q);
	}
	
	public List<Questions> allQuestions() {
		return queRep.findAll();
	}
	
	public List<Tags> allTags() {
		return tagRep.findAll();
	}
	
	public Tags createTag(Tags t) {
		return tagRep.save(t);
	}
	
	public Tags findTag(Long id) {
		Optional<Tags> optionaltype = tagRep.findById(id);
	    if(optionaltype.isPresent()) {
	    	return optionaltype.get();
	    }else {
	        return null;
	    }
	 }
	
	public List<Tags> findSubject(String s) {
		return tagRep.findBySubjectContaining(s);
	}
}
