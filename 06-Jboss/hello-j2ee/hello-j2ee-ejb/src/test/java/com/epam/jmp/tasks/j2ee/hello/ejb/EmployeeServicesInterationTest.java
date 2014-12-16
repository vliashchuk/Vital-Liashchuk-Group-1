package com.epam.jmp.tasks.j2ee.hello.ejb;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.naming.NamingException;

import org.testng.annotations.Test;

import com.epam.jmp.tasks.j2ee.hello.ejb.domain.employee.Address;
import com.epam.jmp.tasks.j2ee.hello.ejb.domain.employee.Employee;
import com.epam.jmp.tasks.j2ee.hello.ejb.domain.employee.EmployeePersonalInfo;
import com.epam.jmp.tasks.j2ee.hello.ejb.domain.employee.EmployeeStatus;
import com.epam.jmp.tasks.j2ee.hello.ejb.domain.employee.Project;
import com.epam.jmp.tasks.j2ee.hello.ejb.domain.employee.Unit;
import com.epam.jmp.tasks.j2ee.hello.ejb.employee.EmployeeServiceRemote;
import com.epam.jmp.tasks.j2ee.hello.ejb.employee.ProjectServiceRemote;
import com.epam.jmp.tasks.j2ee.hello.ejb.employee.UnitServiceRemote;

public class EmployeeServicesInterationTest extends AbstractRemoteTest{

	@Test
	public void test() throws NamingException{
		UnitServiceRemote unitService = (UnitServiceRemote) super.getContext().lookup("hello-j2ee-ear/hello-j2ee-ejb-1.0-SNAPSHOT/UnitServiceBean!com.epam.jmp.tasks.j2ee.hello.ejb.employee.UnitServiceRemote");
		ProjectServiceRemote projectService = (ProjectServiceRemote) super.getContext().lookup("hello-j2ee-ear/hello-j2ee-ejb-1.0-SNAPSHOT/ProjectServiceBean!com.epam.jmp.tasks.j2ee.hello.ejb.employee.ProjectServiceRemote");
		EmployeeServiceRemote employeeService = (EmployeeServiceRemote) super.getContext().lookup("hello-j2ee-ear/hello-j2ee-ejb-1.0-SNAPSHOT/EmployeeServiceBean!com.epam.jmp.tasks.j2ee.hello.ejb.employee.EmployeeServiceRemote");
		
		Unit unit1 = new Unit();
		unit1.setName("unit1");
		unit1 = unitService.createUnit(unit1);
		
		Project project1 = new Project();
		project1.setName("project1");
		project1 = projectService.createProject(project1);
		
		Employee employee1 = new Employee();
		Address adress1 = new Address();
		adress1.setCity("Grodno");
		adress1.setStreet("Gorkogo");
		employee1.setAddress(adress1);
		employee1.setStatus(EmployeeStatus.HIRED);
		EmployeePersonalInfo info1 = new EmployeePersonalInfo();
		info1.setFirstName("Ivan");
		info1.setLastName("Ivanov");
		employee1.setPersonalInfo(info1);
//		employee1.setUnit(unit1);
//		employee1.setProjects(new HashSet<Project>(Arrays.asList(project1)));

		employee1 = employeeService.createEmployee(employee1);
		
		Collection<Employee> employees = employeeService.listAllEmployees();
		for(Employee employee:employees){
			System.out.println(employee);
		}
		Collection<Unit> units = unitService.listAllUnits();
		for(Unit unit:units){
			System.out.println(unit);
		}
		Collection<Project> projects = projectService.listAllProjects();
		for(Project project:projects){
			System.out.println(project);
		}
		
		System.out.println("Adding unit and project...");
		unitService.addEmployeeToUnit(employee1.getId(), unit1.getId());
		projectService.addEmployeeToProject(employee1.getId(), project1.getId());
		
		employees = employeeService.listAllEmployees();
		for(Employee employee:employees){
			System.out.println(employee);
		}
		units = unitService.listAllUnits();
		for(Unit unit:units){
			System.out.println(unit);
		}
		projects = projectService.listAllProjects();
		for(Project project:projects){
			System.out.println(project);
		}
		
		System.out.println("Deleting employee...");
		employeeService.deleteEmployee(employee1.getId());
		
		employees = employeeService.listAllEmployees();
		for(Employee employee:employees){
			System.out.println(employee);
		}
		units = unitService.listAllUnits();
		for(Unit unit:units){
			System.out.println(unit);
		}
		projects = projectService.listAllProjects();
		for(Project project:projects){
			System.out.println(project);
		}
		
		System.out.println("Deleting project...");
		projectService.deleteProject(project1.getId());
		
		employees = employeeService.listAllEmployees();
		for(Employee employee:employees){
			System.out.println(employee);
		}
		units = unitService.listAllUnits();
		for(Unit unit:units){
			System.out.println(unit);
		}
		projects = projectService.listAllProjects();
		for(Project project:projects){
			System.out.println(project);
		}
		
		System.out.println("Deleting unit...");
		unitService.deleteUnit(unit1.getId());
		
		employees = employeeService.listAllEmployees();
		for(Employee employee:employees){
			System.out.println(employee);
		}
		units = unitService.listAllUnits();
		for(Unit unit:units){
			System.out.println(unit);
		}
		projects = projectService.listAllProjects();
		for(Project project:projects){
			System.out.println(project);
		}
		
	}
	
}
