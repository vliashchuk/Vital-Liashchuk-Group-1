package com.epam.jmp.tasks.mvc.controller;

import org.shop.api.UserService;
import org.shop.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.jmp.tasks.mvc.domain.UserList;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model){

		model.addAttribute("users", new UserList(userService.getUsers()));
		
		return "users/list";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String empty(Model model){
		
		model.addAttribute("user", new User());
		return "users/detail";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model){
		
		model.addAttribute("user", userService.getUserById(id));
		
		return "users/detail";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String createOrUpdate(@ModelAttribute("user") User user){
		
		Long id = user.getId();
		
		if(id == null){
			id = userService.registerUser(user);
			
		} else {
			User persisted = userService.getUserById(id);
			if(persisted == null){
				id = userService.registerUser(user);
			} else {
				userService.updateUserProfile(user);
			}
		}
		
		return "redirect:/users/" + id;
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public String update(@ModelAttribute("user") User user){
		
		userService.updateUserProfile(user);
		
		return "redirect:/users/" + user.getId();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id, Model model){
		
		System.out.println("Got user delete request, id: " + id);
		return "redirect:/users";
	}
	
}
