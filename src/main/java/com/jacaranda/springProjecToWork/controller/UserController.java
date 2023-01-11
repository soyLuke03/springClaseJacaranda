package com.jacaranda.springProjecToWork.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.jacaranda.springProjecToWork.model.User;
import com.jacaranda.springProjecToWork.service.UserService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UserController {

	@Autowired
	UserService service;
	
	@GetMapping("/sign_up")
	public String signUp(Model model) {
		model.addAttribute("user",new User());
		return "signup";
	}
	
	@GetMapping("/sign_up/submit")
	public String signUpSubmit(Model model,
			@ModelAttribute("user") User user,
			HttpServletRequest request){
		
		String siteURL = request.getRequestURL().toString();
		siteURL = siteURL.replace(request.getServletPath(), "");
		if(service.checkExist(user)) {
			try {
				service.add(user, siteURL);
			}
			catch (UnsupportedEncodingException e) {
				return "error";
			}
			catch (MessagingException e) {
				return "error";
			}
			return "regisster_success";
		}else {
			return "error";
		}
		
	}
	
}
