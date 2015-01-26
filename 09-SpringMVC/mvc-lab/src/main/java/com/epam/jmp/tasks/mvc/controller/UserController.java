package com.epam.jmp.tasks.mvc.controller;

import org.apache.log4j.Logger;
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

	private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
	
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
		return "users/detail-new";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model){
		
		model.addAttribute("user", userService.getUserById(id));
		return "users/detail";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String create(@ModelAttribute("user") User user){
		
		Long id = user.getId();
		id = userService.registerUser(user);

		LOGGER.debug("Create user POST called(id="
				 + user.getId()
				 + ", name:"
				 + user.getUsername()
				 +")");
		
		return "redirect:/users/" + id;
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public String update(@ModelAttribute("user") User user){
		
		userService.updateUserProfile(user);
		
		LOGGER.debug("Update user PUT called(id="
				 + user.getId()
				 + ", name:"
				 + user.getUsername()
				 +")");
		
		return "redirect:/users/" + user.getId();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id, Model model){
		
		userService.deleteUserProfile(id);
		
		LOGGER.debug("Delete user DELETE called(id="
				 + id
				 +")");
		
		return "redirect:/users";
	}
	
}
