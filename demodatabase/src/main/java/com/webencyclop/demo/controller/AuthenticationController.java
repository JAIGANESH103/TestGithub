/**
 * 
 */
package com.webencyclop.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.webencyclop.demo.model.User;
import com.webencyclop.demo.service.UserService;

@Controller
public class AuthenticationController {
	
	@Autowired
	UserService userService;

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login"); // resources/template/login.html
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		User user =new User();
		modelAndView.addObject("user",user);
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home"); // resources/template/home.html
		return modelAndView;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, Model modelMap) {
		ModelAndView modelAndView=new ModelAndView();
		//check the validation:
		System.out.println("Validation::" +bindingResult.hasErrors());
		if(bindingResult.hasErrors()) {
			System.out.println("Validation::11");
			modelAndView.addObject("successMessage","Please correct the errors in forms");
			modelMap.addAttribute("bindingResult",bindingResult);
		}else if(userService.isUserAlreadyExist(user)){
			modelAndView.addObject("successMessage", "User already Exist");
		}else {
			System.out.println("Save User");
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User is Registered  Successfully");
		}
		modelAndView.addObject("user", new User());
		modelAndView.setViewName("register");
		return modelAndView;
		
	}
}
