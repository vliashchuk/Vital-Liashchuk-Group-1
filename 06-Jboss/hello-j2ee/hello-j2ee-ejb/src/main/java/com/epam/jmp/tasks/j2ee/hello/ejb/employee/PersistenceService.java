package com.epam.jmp.tasks.j2ee.hello.ejb.employee;

import java.util.Collection;

import javax.ejb.Local;

@Local
public interface PersistenceService<T, I> {

    T create(T t);
    T find(I id);
    void delete(I id);
    void update(T t);
    Collection<T> listAll();
}
