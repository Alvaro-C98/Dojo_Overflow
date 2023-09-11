package com.mvc.overflow.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mvc.overflow.models.Questions;
import com.mvc.overflow.models.Tags;
import com.mvc.overflow.services.MainService;

import jakarta.validation.Valid;

@Controller
public class MainController {
	
	private final MainService mainSer;
	public MainController(MainService s) {
		this.mainSer=s;
	}
	
	@GetMapping("/")
	public String home() {
		return "redirect:/dashboard";
	}
	
    @GetMapping("/questions/new")
    public String newQuestion(@ModelAttribute("quest") Questions question) {
        return "newQuestion.jsp";
    }
    
    @PostMapping("/questions/new")
    public String createQuestion(@Valid @ModelAttribute("quest") Questions question, 
    		BindingResult resultQuestion) {
    	   System.out.println(question.getQuestion());
           System.out.println(question.getSubject());
        if (resultQuestion.hasErrors()) {
            return "newQuestion.jsp";
        } else {
        	ArrayList<String> newtag = new ArrayList<String>();
        	List<String> tags = Arrays.asList(question.getSubject().split("\\s*,\\s*"));
        	System.out.println(tags);
        	System.out.println(mainSer.allTags().size());
        	
        	if(mainSer.allTags().size()==0) {
            	for(String tag : tags) {
            		System.out.println("cad: "+tag);
    				Tags newTag = new Tags();
    				System.out.println("Se creo tag");
    				newTag.setSubject(tag);
    				System.out.println("Se seteo el tag con "+tag);
    				mainSer.createTag(newTag);
    				System.out.println("Se creo el tag");
            	}
            	System.out.println("popopo");
            	
            	for(Tags subject : mainSer.allTags()) {
            		subject.setQuestions(question);
            		System.out.println("Se completo");
            	}
	
        	}else {
            	for(String tag : tags) {
            		int b=0;
            		System.out.println("tag: "+tag);
            		for(Tags subject : mainSer.allTags()){
            			System.out.println("subject: "+subject.getSubject());
            			if(tag.equals(subject.getSubject())) {
            				b=1;
            				System.out.println("b="+b);
            				Tags uptag = mainSer.findTag(subject.getId());
            				uptag.setQuestions(question);
            			}
            		}
            		if(b==0) {
            			newtag.add(tag);
            		}
            	}
        	}
            return "redirect:/";
        }
    }
    
    @GetMapping("/dashboard")
    public String index(Model model) {
        List<Questions> question = mainSer.allQuestions();
        model.addAttribute("question", question);
        return "dashboard.jsp";
    }
	
}
