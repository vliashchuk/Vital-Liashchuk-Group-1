package com.epam.jmp.tasks.j2ee.hello.ejb.employee;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.epam.jmp.tasks.j2ee.hello.ejb.domain.employee.Employee;

@Stateless
public class EmployeeServiceBean implements EmployeeService, EmployeeServiceRemote{

	private static final String LIST_All_SQL = "SELECT e FROM Employee e";
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;
	
	@Override
	public Employee createEmployee(Employee employee) {
		entityManager.persist(employee.getPersonalInfo());
		entityManager.persist(employee);
		return employee;
	}

	@Override
	public void deleteEmployee(Long id) {
		Employee e = entityManager.find(Employee.class, id);
		if(e!=null){
			entityManager.remove(e);
		}
	}

	@Override
	public Employee getEmployee(Long id) {
		return entityManager.find(Employee.class, id);
	}

	@Override
	public void updateEmployee(Employee employee) {
		entityManager.merge(employee.getPersonalInfo());
		entityManager.merge(employee);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Employee> listAllEmployees() {
		return entityManager.createQuery(LIST_All_SQL).getResultList();
	}

}
