package com.epam.jmp.tasks.j2ee.hello.ejb.employee;

import javax.ejb.Local;

import com.epam.jmp.tasks.j2ee.hello.ejb.domain.employee.Unit;

@Local
public interface UnitService extends PersistenceService<Unit, Long> {

}
