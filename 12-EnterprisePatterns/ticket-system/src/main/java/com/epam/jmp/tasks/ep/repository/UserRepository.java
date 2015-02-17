package com.epam.jmp.tasks.ep.repository;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.epam.jmp.tasks.ep.domain.User;

public class UserRepository extends AbstractRepository<User, UserRepository.TransactionalUser>{

	protected static class TransactionalUser extends User implements Transactional{

		private ReadWriteLock lock = new ReentrantReadWriteLock();

		private Thread lockingThread = null;
		
		private void read(){
			lock.readLock().lock();;
		}

		private void write(){
			lock.readLock().unlock();
			lock.writeLock().lock();
			lockingThread = Thread.currentThread();
		}
		
		private void release(){
			lock.readLock().unlock();
			lock.writeLock().unlock();
			lockingThread = null;
		}
		
		private boolean isLocked(){
			return lockingThread != null;
		}
		
		private String oldName;
		
		@Override
		public void commit() {
			oldName = super.getName();
			this.release();
		}

		@Override
		public void rollback() {
			super.setName(oldName);
			this.release();
		}
		
		public TransactionalUser(User src){
			super.setName(src.getName());
			oldName = super.getName();
		}
		
		@Override
		public String getName() {
			this.read();
			if(this.isLocked()){
				return oldName;
			}else{
				return super.getName();
			}
			
		}
		@Override
		public void setName(String name) {
			this.write();
			super.setName(name);
		}
		
	}

	@Override
	protected User createEntity(TransactionalUser transactionalEntity) {
		User ret = new User();
		this.copy(transactionalEntity, ret);
		return ret;
	}

	@Override
	protected TransactionalUser createTransactionalEntity(User entity) {
		TransactionalUser ret = new TransactionalUser(entity);
		return ret;
	}

	@Override
	protected void fillWithNewValus(User source, TransactionalUser target) {
		this.copy(source, target);
	}
	
	private void copy(User source, User target){
		target.setName(source.getName());
	}
}
