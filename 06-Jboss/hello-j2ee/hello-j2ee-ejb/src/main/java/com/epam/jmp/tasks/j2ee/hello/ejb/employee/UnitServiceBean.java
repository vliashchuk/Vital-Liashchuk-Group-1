package com.epam.jmp.tasks.j2ee.hello.ejb.employee;

import javax.ejb.Stateless;

import com.epam.jmp.tasks.j2ee.hello.ejb.domain.employee.Unit;

@Stateless
public class UnitServiceBean extends PersistenceServiceImpl<Unit, Long> implements UnitService, UnitServiceRemote{

	public UnitServiceBean() {
		super(Unit.class);
	}

}
