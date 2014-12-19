package com.epam.jmp.tasks.j2ee.hello.ejb.employee;

import javax.ejb.Remote;

import com.epam.jmp.tasks.j2ee.hello.ejb.domain.employee.Unit;

@Remote
public interface UnitServiceRemote extends PersistenceService<Unit, Long> {

}
