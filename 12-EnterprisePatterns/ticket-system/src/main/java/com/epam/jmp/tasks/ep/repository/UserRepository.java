package com.epam.jmp.tasks.ep.repository;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.epam.jmp.tasks.ep.domain.User;

public class UserRepository extends AbstractRepository<User, UserRepository.TransactionalUser>{

	protected static class TransactionalUser extends User implements Transactional{

		private ReadWriteLock lock = new ReentrantReadWriteLock();

		TransactionManager transactionManager;
		
		private Transaction transaction;
		
		private void read(){
			lock.readLock().lock();;
		}

		private void write(){
			
			lock.readLock().unlock();
			lock.writeLock().lock();
			
			if(transaction==null){
				transaction = transactionManager.createTransaction();
			}
			
			if(transaction.isHoldingThread()){

			} else {
				transaction.waitUntilTransactionEnds();
				switch (transaction.getStateId()) {
				case 1: // commit, cache old values
					oldName = super.getName();
					break;
				case 2: // rollback, restore values
					super.setName(oldName);
					break;
				default: // rollback, restore values
					super.setName(oldName);
					break;
				}
				transaction = transactionManager.createTransaction();
			}
			
		}
		
		private void release(){
			lock.readLock().unlock();
			lock.writeLock().unlock();
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
		}
		
		@Override
		public String getName() {
			this.read();
			if(transaction.isHoldingThread()){
				return super.getName();
			}else{
				return oldName;
			}
			
		}
		@Override
		public void setName(String name) {
			this.write();
			super.setName(name);
		}

		@Override
		public void setTransactionManager(TransactionManager transactionManager) {
			this.transactionManager = transactionManager;
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
