package com.epam.jmp.tasks.ddm.repository;

import java.util.ArrayList;
import java.util.List;

import com.epam.jmp.tasks.ddm.domain.Entity;

public abstract class AbstractRepository<T extends Entity> {
	
	private List<T> items = new ArrayList<>();
	
	private Long lastId = 0l;
	
	private synchronized Long nextId(){
		return lastId++;
	}
	
	protected T doGetById(Long id){
		for(T item:items){
			if(item.getId().equals(id)){
				return item;
			}
		}
		return null;
	}
	
	protected List<T> doGetAll(){
		return items;
	}

	protected void doAdd(T item){
		item.setId(nextId());
		items.add(item);
	}
	
	protected void doRemove(T item){
		T i= doGetById(item.getId());
		items.remove(i);
	}
	
	public abstract void add(T item);
	public abstract void update(T item);
	public abstract T getById(Long id);
	public abstract List<T> getAllByCriteria(Criteria<T> criteria);
	
	public void remove(T item) {
		doRemove(item);
	}
	
	public List<T> doGetAllByCriteria(Criteria<T> criteria){
		List<T> ret = new ArrayList<>();
		for(T item:items){
			if(criteria.match(item)){
				ret.add(item);
			}
		}
		return ret;
	}

}
