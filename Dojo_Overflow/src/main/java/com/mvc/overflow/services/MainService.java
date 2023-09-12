package com.mvc.overflow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mvc.overflow.models.Answers;
import com.mvc.overflow.models.Questions;
import com.mvc.overflow.models.Tags;
import com.mvc.overflow.repositories.AnswerRepository;
import com.mvc.overflow.repositories.QuestionRepository;
import com.mvc.overflow.repositories.TagRepository;

@Service
public class MainService {
	private final QuestionRepository queRep;
	private final TagRepository tagRep;
	private final AnswerRepository ansRep;

	public MainService(QuestionRepository q, TagRepository t, AnswerRepository a) {
		this.queRep=q;
		this.tagRep=t;
		this.ansRep=a;
	}
	
	public Questions createQuestion(Questions q) {
		return queRep.save(q);
	}
	
	public List<Questions> allQuestions() {
		return queRep.findAll();
	}
	
	public Questions findQuestion(Long id) {
		Optional<Questions> optionaltype = queRep.findById(id);
	    if(optionaltype.isPresent()) {
	    	return optionaltype.get();
	    }else {
	        return null;
	    }
	 }
	
    public Questions updateQuestion(Questions q) {
    	Questions temp = findQuestion(q.getId());
    	return queRep.save(temp);
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
	
	public Tags findSubject(String s) {
		return tagRep.findBySubject(s);
	}
	
	public Answers createAnswer(Answers a) {
		return ansRep.save(a);
	}
	
	public Answers findAnswer(String s) {
		return ansRep.findByAnswer(s);
	}
}
