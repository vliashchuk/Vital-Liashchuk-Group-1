package com.epam.jmp.tasks.j2ee.hello.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.epam.jmp.tasks.j2ee.hello.ejb.domain.Greeting;
import com.epam.jmp.tasks.j2ee.hello.ejb.domain.GreetingEntity;

@Stateless
public class GreetingServiceBean implements GreetingService{

	private static final String LIST_GREETING_SQL = "SELECT g FROM GreetingEntity g";
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;
	
	@Override
	public Greeting createGreeting(String text) {
		GreetingEntity greeting = new GreetingEntity();
		greeting.setText(text);
		entityManager.persist(greeting);
		return greeting;
	}

	@Override
	public Greeting getGreeting(int id) {
		return entityManager.find(GreetingEntity.class, id);
	}

	@Override
	public void updateGreeting(Greeting greeting) {
		entityManager.merge(new GreetingEntity(greeting));
	}

	@Override
	public void deleteGreeting(int id) {
		GreetingEntity greetingEntity = entityManager.find(GreetingEntity.class, id);
		if(greetingEntity!=null){
			entityManager.remove(greetingEntity);
		}
	}

	@Override
	public List<Greeting> listGreetings() {
		return entityManager.createQuery(LIST_GREETING_SQL).getResultList();
	}

}
