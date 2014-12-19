package com.epam.jmp.tasks.j2ee.hello.ejb.employee;

import javax.ejb.Remote;

import com.epam.jmp.tasks.j2ee.hello.ejb.domain.employee.Employee;

@Remote
public interface EmployeeServiceRemote extends PersistenceService<Employee, Long> {

}
