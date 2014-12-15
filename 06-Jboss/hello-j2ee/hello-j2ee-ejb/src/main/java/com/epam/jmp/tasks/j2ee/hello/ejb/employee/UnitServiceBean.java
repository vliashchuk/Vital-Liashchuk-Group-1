package com.epam.jmp.tasks.j2ee.hello.ejb.employee;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.epam.jmp.tasks.j2ee.hello.ejb.domain.employee.Employee;
import com.epam.jmp.tasks.j2ee.hello.ejb.domain.employee.Unit;

@Stateless
public class UnitServiceBean implements UnitService, UnitServiceRemote{

	private static final String LIST_All_SQL = "SELECT e FROM Unit e";
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;
	
	@Override
	public Unit createUnit(Unit unit) {
		entityManager.persist(unit);
		return unit;
	}

	@Override
	public void deleteUnit(Long id) {
		Unit u = entityManager.find(Unit.class, id);
		entityManager.remove(u);
	}

	@Override
	public Unit getUnit(Long id) {
		return entityManager.find(Unit.class, id);
	}

	@Override
	public void updateUnit(Unit unit) {
		entityManager.merge(unit);
	}

	@Override
	public void addEmployeeToUnit(Long employeeId, Long unitId) {
		Employee e = entityManager.find(Employee.class, employeeId);
		Unit u = entityManager.find(Unit.class, unitId);
		
		u.getEmployees().add(e);
		e.setUnit(u);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Unit> listAllUnits() {
		return entityManager.createQuery(LIST_All_SQL).getResultList();
	}

}
