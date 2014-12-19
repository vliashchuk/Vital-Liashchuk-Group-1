package com.epam.jmp.tasks.j2ee.hello.ejb.employee;

import javax.ejb.Local;

import com.epam.jmp.tasks.j2ee.hello.ejb.domain.employee.Employee;

@Local
public interface EmployeeService extends PersistenceService<Employee, Long> {

}
