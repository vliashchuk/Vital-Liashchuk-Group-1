package com.epam.jmp.tasks.j2ee.hello.ejb.employee;

import javax.ejb.Stateless;

import com.epam.jmp.tasks.j2ee.hello.ejb.domain.employee.Employee;

@Stateless
public class EmployeeServiceBean extends PersistenceServiceImpl<Employee, Long> implements EmployeeService, EmployeeServiceRemote {

	public EmployeeServiceBean() {
		super(Employee.class);
	}
}
