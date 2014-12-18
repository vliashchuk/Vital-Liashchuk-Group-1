package com.epam.jmp.tasks.j2ee.hello.ejb.employee;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PersistenceServiceBean implements PersistenceService, PersistenceServiceRemote {

	private static final String LIST_All_SQL = "SELECT e FROM <entity> e";
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;
	
	@Override
	public <T> T create(T t) {
        this.entityManager.persist(t);
        return t;
	}

	@Override
	public <T> T find(Class<T> type, Object id) {
		return entityManager.find(type, id);
	}

	@Override
	public <T> void delete(Class<T> type, Object id) {
       T ref = entityManager.getReference(type, id);
       if(ref!=null){
    	   entityManager.remove(ref);
       }
	}

	@Override
	public <T> void update(T t) {
		entityManager.merge(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Collection<T> listAll(Class<T> type) {
		return entityManager.createQuery(
				LIST_All_SQL.replace("<entity>", type.getSimpleName()))
					.getResultList();
	}

}
