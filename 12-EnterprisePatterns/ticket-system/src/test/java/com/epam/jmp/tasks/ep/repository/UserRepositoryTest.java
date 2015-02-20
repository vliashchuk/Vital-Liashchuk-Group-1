package com.epam.jmp.tasks.ep.repository;

import java.util.List;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.epam.jmp.tasks.ep.domain.User;

public class UserRepositoryTest {
	
	UserRepository userRepository;
	
	@BeforeSuite
	public void prepare(){
		userRepository = new UserRepository();
	}
	
	@Test(invocationCount=2, threadPoolSize=2)
	public void test(){
		
		List<User> allUsers;
		
		User user1 = new User();
		user1.setName("user1");
		
		User user2 = new User();
		user1.setName("user2");
		
		allUsers = userRepository.selectAll();
		
		userRepository.insert(user1);
		userRepository.insert(user2);
		
		allUsers = userRepository.selectAll();
		
		userRepository.commit();
	}
	
}
