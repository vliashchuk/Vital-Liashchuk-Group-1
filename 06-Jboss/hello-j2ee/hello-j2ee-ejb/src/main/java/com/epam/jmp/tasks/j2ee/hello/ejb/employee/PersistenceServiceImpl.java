package com.epam.jmp.tasks.j2ee.hello.ejb.employee;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PersistenceServiceImpl<T, I> implements PersistenceService<T, I> {

	private static final String LIST_All_SQL = "SELECT e FROM <entity> e";
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;

	private Class<T> type;
	
	public PersistenceServiceImpl(Class<T> type){
		this.type = type;
	}
	
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public T create(T t) {
        this.entityManager.persist(t);
        return t;
	}

	@Override
	public T find(I id) {
		return entityManager.find(type, id);
	}

	@Override
	public void delete(I id) {
		T ref = entityManager.getReference(type, id);
		if (ref != null) {
			entityManager.remove(ref);
		}

	}

	@Override
	public void update(T t) {
		entityManager.merge(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<T> listAll() {
		return entityManager.createQuery(
				LIST_All_SQL.replace("<entity>", type.getSimpleName()))
				.getResultList();
	}

}
