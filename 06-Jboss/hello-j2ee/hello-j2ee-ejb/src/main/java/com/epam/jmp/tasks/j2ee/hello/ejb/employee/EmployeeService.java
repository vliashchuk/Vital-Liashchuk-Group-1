package com.epam.jmp.tasks.j2ee.hello.ejb.employee;

import java.util.Collection;

import javax.ejb.Local;

import com.epam.jmp.tasks.j2ee.hello.ejb.domain.employee.Employee;

@Local
public interface EmployeeService {

	Employee createEmployee(Employee employee);

	void deleteEmployee(Long id);

	Employee getEmployee(Long id);

	void updateEmployee(Employee employee);

	Collection<Employee> listAllEmployees();
}
