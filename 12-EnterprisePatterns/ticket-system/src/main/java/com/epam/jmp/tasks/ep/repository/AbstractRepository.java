package com.epam.jmp.tasks.ep.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractRepository <E extends Entity, T extends Transactional & Entity> {

	private Map<Long, T> persistedEntities = new ConcurrentHashMap<>();
	
	private Long currentId = 0l; 
	
	protected abstract E createEntity(T transactionalEntity);
	protected abstract T createTransactionalEntity(E entity);
	protected abstract void fillWithNewValus(E source, T target);
	
	private Long nextId(){
		synchronized (currentId) {
			return currentId++;
		}
	}
	
	public E insert(E item){
		T transactionalEntity = this.createTransactionalEntity(item);
		transactionalEntity.setId(this.nextId());
		persistedEntities.put(transactionalEntity.getId(), transactionalEntity);
		item.setId(transactionalEntity.getId());
		return item;
	};
	
	void update(E item){
		T transactionalEntity = persistedEntities.get(item.getId());
		fillWithNewValus(item, transactionalEntity);
	}
	
	void delete(Long id){
		persistedEntities.remove(id);
	}
	
	E select(Long id){
		T transactionalEntity = persistedEntities.get(id);
		return createEntity(transactionalEntity);
	} 
	
	List<E> selectAll(Long id){
		List<E> ret = new ArrayList<>();
		for(T transactionalEntity:persistedEntities.values()){
			ret.add(createEntity(transactionalEntity));
		}
		return ret;
	} 
}
