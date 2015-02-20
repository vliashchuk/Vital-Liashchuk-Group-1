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
//			lock.readLock().lock();;
		}

		private void write(){
			
			lock.writeLock().lock();
			
			if(transaction==null){
				transaction = transactionManager.getTransaction();
				transaction.addParticipant(this);
				oldName = null; // backup old values
			}
			
			if(!transaction.isHoldingThread()){
				//transaction wasn't completed successfully

				switch (transaction.getStateId()) {
				case 1: // commit, clean old values
					oldName = null;
					break;
				case 2: // rollback, restore values
					super.setName(oldName);
					deleted=oldDeleted;
					break;
				default: // rollback, restore values
					super.setName(oldName);
					deleted=oldDeleted;
					break;
				}
				transaction = transactionManager.getTransaction();
				transaction.addParticipant(this);
			}
			
		}
		
		private void release(){
//			lock.readLock().unlock();
			lock.writeLock().unlock();
		}
		private Boolean inserted = false;
		private Boolean oldInserted = false;
		private Boolean deleted = false;
		private Boolean oldDeleted = false;
		
		private String oldName;
		
		@Override
		public void commit() {
			transaction = null;
			this.release();
		}

		@Override
		public void rollback() {
			//restore values
			super.setName(oldName);
			deleted=oldDeleted;
			transaction = null;
			this.release();
		}
		
		public TransactionalUser(User src, TransactionManager transactionManager){
			this.transactionManager = transactionManager;
			super.setName(src.getName());
			
			this.write();
			oldInserted = inserted;
			inserted = true;
		}
		
		@Override
		public String getName() {
			this.read();
			if(transaction == null){
				return super.getName();
			} else{
				if(transaction.isHoldingThread()){
					return super.getName();
				} else{
					if(oldName == null){
						return super.getName();
					} else {
						return oldName;
					}
				}
			}
		}
		@Override
		public void setName(String name) {
			this.write();
			oldName = super.getName();
			super.setName(name);
		}

//		@Override
//		public void setTransactionManager(TransactionManager transactionManager) {
//			this.transactionManager = transactionManager;
//		}


		
		@Override
		public void markDeleted() {
			this.write();
			oldDeleted = deleted;
			deleted = true;
		}

		@Override
		public boolean isInserted() {
			this.read();
			if(transaction == null){
				return inserted;
			} else{
				if(transaction.isHoldingThread()){
					return inserted;
				} else{
					if(oldInserted == null){
						return inserted;
					} else {
						return oldInserted;
					}
				}
			}
		}

		@Override
		public boolean isDeleted() {
			this.read();
			if(transaction == null){
				return deleted;
			} else{
				if(transaction.isHoldingThread()){
					return deleted;
				} else{
					if(oldDeleted == null){
						return deleted;
					} else {
						return oldDeleted;
					}
				}
			}
		}
		
	}

	@Override
	protected User createEntity(TransactionalUser transactionalEntity) {
		User ret = new User();
		this.copy(transactionalEntity, ret);
		return ret;
	}

	@Override
	protected TransactionalUser createTransactionalEntity(User entity, TransactionManager transactionManager) {
		TransactionalUser ret = new TransactionalUser(entity, transactionManager);
		return ret;
	}

	@Override
	protected void fillWithNewValus(User source, TransactionalUser target) {
		this.copy(source, target);
	}
	
	private void copy(User source, User target){
		if(!target.equals(source)){
			target.setName(source.getName());
		}
	}
}
