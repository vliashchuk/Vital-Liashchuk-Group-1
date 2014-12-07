package com.epam.jmp.tasks.j2ee.hello.ejb;

import java.util.List;

import javax.ejb.Local;

import com.epam.jmp.tasks.j2ee.hello.ejb.domain.Greeting;

@Local
public interface GreetingService {

	Greeting createGreeting(String text);
	Greeting getGreeting(int id);
	void updateGreeting(Greeting greeting);
	void deleteGreeting(int id);
	List<Greeting> listGreetings();
	
}
