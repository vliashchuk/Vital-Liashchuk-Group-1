package com.epam.jmp.tasks.j2ee.hello.ejb.employee;

import java.util.Collection;

import javax.ejb.Remote;

@Remote
public interface PersistenceServiceRemote {
	
    <T> T create(T t);
    <T> T find(Class<T> type, Object id);
    <T> void delete(Class<T> type, Object id);
    <T> void update(T t);
    <T> Collection<T> listAll(Class<T> type);	
}
