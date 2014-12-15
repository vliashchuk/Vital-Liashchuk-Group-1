package com.epam.jmp.tasks.j2ee.hello.ejb.employee;

import java.util.Collection;

import javax.ejb.Remote;

import com.epam.jmp.tasks.j2ee.hello.ejb.domain.employee.Unit;

@Remote
public interface UnitServiceRemote {
	
	Unit createUnit(Unit unit);

	void deleteUnit(Long id);

	Unit getUnit(Long id);

	void updateUnit(Unit unit);

	void addEmployeeToUnit(Long employeeId, Long unitId);
	
	Collection<Unit> listAllUnits();
}
