package com.epam.jmp.tasks.mvc.controller;

import org.shop.api.UserService;
import org.shop.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model){
		
		model.addAttribute("users", userService.getUsers());
		
		return "users/list";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model){
		
		model.addAttribute("user", userService.getUserById(id));
		
		return "users/detail";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String create(User user){
		
		Long id = userService.registerUser(user);
		
		return "redirect:/users/" + id;
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public String update(User user){
		
		userService.updateUserProfile(user);
		
		return "redirect:/users/" + user.getId();
	}
	
}
