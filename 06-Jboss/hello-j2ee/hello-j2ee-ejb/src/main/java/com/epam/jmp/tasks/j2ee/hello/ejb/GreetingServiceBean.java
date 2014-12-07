package com.epam.jmp.tasks.j2ee.hello.ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;

import com.epam.jmp.tasks.j2ee.hello.ejb.domain.Greeting;
import com.epam.jmp.tasks.j2ee.hello.ejb.domain.SimpleGreeting;

@Stateful
public class GreetingServiceBean implements GreetingService, Serializable{

	private List<SimpleGreeting> greetings = new ArrayList<>();
	private int lastId = 0;
	
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -339751859948964033L;

	@Override
	public Greeting createGreeting(String text) {
		SimpleGreeting g = new SimpleGreeting();
		g.setId(lastId++);
		g.setText(text);
		greetings.add(g);
		return g;
	}

	@Override
	public Greeting getGreeting(int id) {
		return doGetGreeting(id);
	}

	private SimpleGreeting doGetGreeting(int id) {
		for(SimpleGreeting greeting:greetings){
			if(greeting.getId()==id){
				return greeting;
			}
		}
		return null;
	}

	@Override
	public void updateGreeting(Greeting greeting) {
		SimpleGreeting g = doGetGreeting(greeting.getId());
		g.setText(greeting.getText());
	}

	@Override
	public void deleteGreeting(int id) {
		SimpleGreeting g = doGetGreeting(id);
		greetings.remove(g);
	}

	@Override
	public List<Greeting> listGreetings() {
		return new ArrayList<Greeting>(greetings);
	}

}
