package com.mvc.overflow.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mvc.overflow.models.Answers;
import com.mvc.overflow.models.QuestionTag;
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
    public String newQuestion(@ModelAttribute("quest") QuestionTag question) {
        return "newQuestion.jsp";
    }
    
    @PostMapping("/questions/new")
    public String createQuestion(@ModelAttribute("quest") QuestionTag question, RedirectAttributes redirect, Model model) {
        if (question.getQuestion().getQuestion().isBlank() || question.getTag().getSubject().isBlank()) {
        	model.addAttribute("error","Fields must not be empty");
            return "newQuestion.jsp";
        } else {
        	List<String> tagStrings = Arrays.asList(question.getTag().getSubject().split("\\s*,\\s*"));
        	List<Tags> tags = new ArrayList<>();
        	Questions newQuestion = new Questions();
        	for(String tag : tagStrings) {
        		Tags tagExist = mainSer.findSubject(tag);
        		if(tagExist==null) {
        			Tags newTag = new Tags();
        			newTag.setSubject(tag);
        			tags.add(newTag);
        		}else {
        			tags.add(tagExist);
        		}
        	}
        	for(Tags tag : tags) {
        		mainSer.createTag(tag);
        	}
        	
        	newQuestion.setTags(tags);
        	newQuestion.setQuestion(question.getQuestion().getQuestion());
        	mainSer.createQuestion(newQuestion);
        	
            return "redirect:/";
        }
    }
    
    @GetMapping("/dashboard")
    public String index(Model model) {
        List<Questions> question = mainSer.allQuestions();
        model.addAttribute("question", question);
        return "dashboard.jsp";
    }
    
    @GetMapping("/question/{id}")
    public String newQuestion(@ModelAttribute("ans") Answers qanswer,
    		@PathVariable("id") Long id,
    		Model model) {
    	Questions question = mainSer.findQuestion(id);
    	model.addAttribute("question",question);
        return "newAnswer.jsp";
    }
    
    @PostMapping("/question/{id}")
    public String createAnswer(@Valid @ModelAttribute("ans") Answers answer, BindingResult result,
    		@PathVariable("id") Long id) {
        if (result.hasErrors()) {
            return "newAnswer.jsp";
        } else {
        	Questions question = mainSer.findQuestion(id);
        	List<Answers> answers = question.getAnswer();
            int b = 0;
            for(Answers a : answers) {
                if(a.getAnswer().equals(answer.getAnswer())) {
                	b=1;
                }
            }
            if(b==0) {
            	answer.setQuestion(question);
            	mainSer.createAnswer(answer);
            	answers.add(answer);
                question.setAnswer(answers);
                mainSer.updateQuestion(question);
            }
            return "redirect:/dashboard";
        }
    }
	
}
