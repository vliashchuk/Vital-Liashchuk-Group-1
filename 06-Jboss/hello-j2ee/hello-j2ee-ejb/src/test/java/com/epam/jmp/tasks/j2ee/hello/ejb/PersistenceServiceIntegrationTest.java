package com.epam.jmp.tasks.j2ee.hello.ejb;

import java.util.HashSet;

import javax.naming.Context;
import javax.naming.NamingException;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.unitils.reflectionassert.ReflectionAssert;

import com.epam.jmp.tasks.j2ee.hello.ejb.domain.employee.Address;
import com.epam.jmp.tasks.j2ee.hello.ejb.domain.employee.Employee;
import com.epam.jmp.tasks.j2ee.hello.ejb.domain.employee.EmployeePersonalInfo;
import com.epam.jmp.tasks.j2ee.hello.ejb.domain.employee.EmployeeStatus;
import com.epam.jmp.tasks.j2ee.hello.ejb.domain.employee.Project;
import com.epam.jmp.tasks.j2ee.hello.ejb.domain.employee.Unit;
import com.epam.jmp.tasks.j2ee.hello.ejb.employee.EmployeeServiceRemote;
import com.epam.jmp.tasks.j2ee.hello.ejb.employee.ProjectServiceRemote;
import com.epam.jmp.tasks.j2ee.hello.ejb.employee.UnitServiceRemote;

@Test(singleThreaded=true)
public class PersistenceServiceIntegrationTest extends AbstractRemoteTest {
	
	private int sequence = 0;
	Context context;
	private EmployeeServiceRemote employeeService;
	private UnitServiceRemote unitService;
	private ProjectServiceRemote projectService;
	
	@BeforeTest
	public void prepare() throws NamingException{
		context = super.getContext();
		employeeService = (EmployeeServiceRemote) context
				.lookup("hello-j2ee-ear/hello-j2ee-ejb-1.0-SNAPSHOT/EmployeeServiceBean!com.epam.jmp.tasks.j2ee.hello.ejb.employee.EmployeeServiceRemote");
		unitService = (UnitServiceRemote) context
				.lookup("hello-j2ee-ear/hello-j2ee-ejb-1.0-SNAPSHOT/UnitServiceBean!com.epam.jmp.tasks.j2ee.hello.ejb.employee.UnitServiceRemote");
		projectService = (ProjectServiceRemote) context
				.lookup("hello-j2ee-ear/hello-j2ee-ejb-1.0-SNAPSHOT/ProjectServiceBean!com.epam.jmp.tasks.j2ee.hello.ejb.employee.ProjectServiceRemote");

	}
	
	private Employee createEmployee(){
		sequence++;
		Employee employee = new Employee();
		Address adress = new Address();
		adress.setCity("Grodno" + sequence);
		adress.setStreet("Gorkogo" + sequence);
		employee.setAddress(adress);
		employee.setStatus(EmployeeStatus.HIRED);
		EmployeePersonalInfo info = new EmployeePersonalInfo();
		info.setFirstName("Ivan" + sequence);
		info.setLastName("Ivanov" + sequence);
		employee.setPersonalInfo(info);
		employee.setProjects(new HashSet<Project>());
		return employee;
	}
	
	private Unit createUnit(){
		sequence++;
		Unit unit = new Unit();
		unit.setName("Java unit " + sequence);
		unit.setEmployees(new HashSet<Employee>());
		return unit;
	}
	
	private Project createProject(){
		sequence++;
		Project project = new Project();
		project.setName("Some project " + sequence);
		project.setEmployees(new HashSet<Employee>());
		return project;
	}
	
	@Test
	public void testEmployeeCreation(){
		Employee employee = createEmployee();
	    employee =  employeeService.create(employee);
		Employee persisted = employeeService.find(employee.getId());
		ReflectionAssert.assertLenientEquals(employee, persisted);
		employeeService.delete(employee.getId());
		
	}
	
	@Test
	public void testEmployeeDeletion(){
		Employee employee = createEmployee();
	    employee =  employeeService.create(employee);
		Employee persisted = employeeService.find(employee.getId());
		ReflectionAssert.assertLenientEquals(employee, persisted);
		employeeService.delete(employee.getId());
		persisted = employeeService.find(employee.getId());
		Assert.assertNull(persisted);
		
	}
	
	@Test
	public void testEmployeeModification(){
		Employee employee = createEmployee();
	    employee =  employeeService.create(employee);
		
		employee.setStatus(EmployeeStatus.FIRED);
		employee.getAddress().setCity(employee.getAddress().getCity() + "_Changed");
		employee.getAddress().setStreet(employee.getAddress().getStreet() + "_Changed");
		employee.getPersonalInfo()
			.setFirstName(employee.getPersonalInfo().getFirstName() + "_Changed");
		employee.getPersonalInfo()
			.setLastName(employee.getPersonalInfo().getLastName() + "_Changed");
		
		employeeService.update(employee);
		Employee persisted = employeeService.find(employee.getId());
		ReflectionAssert.assertLenientEquals(employee, persisted);
		
		employeeService.delete(employee.getId());
	}
	
	@Test
	public void testUnitCreation(){
		
		Unit unit = createUnit();
		unit = unitService.create(unit);
		Unit persisted = unitService.find(unit.getId());
		ReflectionAssert.assertLenientEquals(unit, persisted);
		unitService.delete(unit.getId());
	}
	
	@Test
	public void testUnitDeletion(){
		
		Unit unit = createUnit();
		unit = unitService.create(unit);
		Unit persisted = unitService.find(unit.getId());
		ReflectionAssert.assertLenientEquals(unit, persisted);
		unitService.delete(unit.getId());
		persisted = unitService.find(unit.getId());
		Assert.assertNull(persisted);
	}
	
	@Test
	public void testUnitModification(){
		
		Unit unit = createUnit();
		unit = unitService.create(unit);	
		
		Employee employee = createEmployee();
	    employee =  employeeService.create(employee);
		
	    employee.setUnit(unit);
	    unit.getEmployees().add(employee);
	    unit.setName(unit.getName() + "_Changed");

	    employeeService.update(employee);
	    Unit persisted = unitService.find(unit.getId());
		ReflectionAssert.assertLenientEquals(unit, persisted);
		
		employee.setUnit(null);
		employeeService.update(employee);
		unitService.delete(unit.getId());
		persisted = unitService.find(unit.getId());
		Assert.assertNull(persisted);
	}
	
	@Test
	public void testProjectCreation(){
		
		Project project = createProject();
		project = projectService.create(project);
		Project persisted = projectService.find(project.getId());
		ReflectionAssert.assertLenientEquals(project, persisted);
		projectService.delete(project.getId());
	}
	
	@Test
	public void testProjectDeletion(){
		
		Project project = createProject();
		project = projectService.create(project);
		Project persisted = projectService.find(project.getId());
		ReflectionAssert.assertLenientEquals(project, persisted);
		projectService.delete(project.getId());
		persisted = projectService.find(project.getId());
		Assert.assertNull(persisted);
	}
	
	@Test
	public void testProjectModification(){
		
		Project project = createProject();
		project = projectService.create(project);
		
		Employee employee = createEmployee();
	    employee =  employeeService.create(employee);
		
	    employee.getProjects().add(project);
	    project.getEmployees().add(employee);
	    project.setName(project.getName() + "_Changed");

	    employeeService.update(employee);
	    Project persisted = projectService.find(project.getId());
		ReflectionAssert.assertLenientEquals(project, persisted);
		
		employee.getProjects().remove(project);
		employeeService.update(employee);
		projectService.delete(project.getId());
		persisted = projectService.find(project.getId());
		Assert.assertNull(persisted);
	}
}
