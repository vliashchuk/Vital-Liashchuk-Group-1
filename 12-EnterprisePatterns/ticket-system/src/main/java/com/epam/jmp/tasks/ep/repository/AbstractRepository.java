package com.epam.jmp.tasks.ep.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractRepository <E extends Entity, T extends Transactional & Entity> {

	TransactionManager transactionManager;
	
	private Map<Long, T> persistedEntities = new ConcurrentHashMap<>();
	
	private Long currentId = 0l; 
	
	protected abstract E createEntity(T transactionalEntity);
	protected abstract T createTransactionalEntity(E entity, TransactionManager transactionManager);
	protected abstract void fillWithNewValus(E source, T target);
	
	public AbstractRepository(){
		transactionManager = new TransactionManager();
	}
	
	private Long nextId(){
		synchronized (currentId) {
			return currentId++;
		}
	}
	
	public E insert(E item){
		T transactionalEntity = this.createTransactionalEntity(item, transactionManager);
		transactionalEntity.setId(this.nextId());
		persistedEntities.put(transactionalEntity.getId(), transactionalEntity);
		item.setId(transactionalEntity.getId());
		return item;
	};
	
	public void update(E item){
		T transactionalEntity = persistedEntities.get(item.getId());
		fillWithNewValus(item, transactionalEntity);
	}
	
	public void delete(Long id){
		T transactionalEntity = persistedEntities.get(id);
		transactionalEntity.markDeleted();;
	}
	
	public E select(Long id){
		T transactionalEntity = persistedEntities.get(id);
		if (transactionalEntity.isInserted()
				&& !transactionalEntity.isDeleted()) {

			return createEntity(transactionalEntity);

		} else {
			return null;
		}
	} 
	
	public List<E> selectAll(){
		List<E> ret = new ArrayList<>();
		for(T transactionalEntity:persistedEntities.values()){
			if (transactionalEntity.isInserted()
					&& !transactionalEntity.isDeleted()) {

				ret.add(createEntity(transactionalEntity));
			}
		}
		return ret;
	} 
	
	public void commit(){
		transactionManager.getTransaction().commit();
	}
	
	public void rollback(){
		transactionManager.getTransaction().rollback();
	}
	
}
