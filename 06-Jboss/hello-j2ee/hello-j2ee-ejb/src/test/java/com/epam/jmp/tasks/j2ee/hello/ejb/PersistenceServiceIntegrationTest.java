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
import com.epam.jmp.tasks.j2ee.hello.ejb.employee.PersistenceServiceRemote;

@Test(singleThreaded=true)
public class PersistenceServiceIntegrationTest extends AbstractRemoteTest {
	
	private int sequence = 0;
	
	private PersistenceServiceRemote persistenceService;
	private Context context;
	
	@BeforeTest
	public void prepare() throws NamingException{
		context = super.getContext();
		persistenceService = (PersistenceServiceRemote) context
				.lookup("hello-j2ee-ear/hello-j2ee-ejb-1.0-SNAPSHOT/PersistenceServiceBean!com.epam.jmp.tasks.j2ee.hello.ejb.employee.PersistenceServiceRemote");

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
	    employee =  persistenceService.create(employee);
		Employee persisted = persistenceService.find(Employee.class, employee.getId());
		ReflectionAssert.assertLenientEquals(employee, persisted);
		persistenceService.delete(Employee.class, employee.getId());
		
	}
	
	@Test
	public void testEmployeeDeletion(){
		Employee employee = createEmployee();
	    employee =  persistenceService.create(employee);
		Employee persisted = persistenceService.find(Employee.class, employee.getId());
		ReflectionAssert.assertLenientEquals(employee, persisted);
		persistenceService.delete(Employee.class, employee.getId());
		persisted = persistenceService.find(Employee.class, employee.getId());
		Assert.assertNull(persisted);
		
	}
	
	@Test
	public void testEmployeeModification(){
		Employee employee = createEmployee();
	    employee =  persistenceService.create(employee);
		
		employee.setStatus(EmployeeStatus.FIRED);
		employee.getAddress().setCity(employee.getAddress().getCity() + "_Changed");
		employee.getAddress().setStreet(employee.getAddress().getStreet() + "_Changed");
		employee.getPersonalInfo()
			.setFirstName(employee.getPersonalInfo().getFirstName() + "_Changed");
		employee.getPersonalInfo()
			.setLastName(employee.getPersonalInfo().getLastName() + "_Changed");
		
		persistenceService.update(employee);
		Employee persisted = persistenceService.find(Employee.class, employee.getId());
		ReflectionAssert.assertLenientEquals(employee, persisted);
		
		persistenceService.delete(Employee.class, employee.getId());
	}
	
	@Test
	public void testUnitCreation(){
		
		Unit unit = createUnit();
		unit = persistenceService.create(unit);
		Unit persisted = persistenceService.find(Unit.class, unit.getId());
		ReflectionAssert.assertLenientEquals(unit, persisted);
		persistenceService.delete(Unit.class, unit.getId());
	}
	
	@Test
	public void testUnitDeletion(){
		
		Unit unit = createUnit();
		unit = persistenceService.create(unit);
		Unit persisted = persistenceService.find(Unit.class, unit.getId());
		ReflectionAssert.assertLenientEquals(unit, persisted);
		persistenceService.delete(Unit.class, unit.getId());
		persisted = persistenceService.find(Unit.class, unit.getId());
		Assert.assertNull(persisted);
	}
	
	@Test
	public void testUnitModification(){
		
		Unit unit = createUnit();
		unit = persistenceService.create(unit);	
		
		Employee employee = createEmployee();
	    employee =  persistenceService.create(employee);
		
	    employee.setUnit(unit);
	    unit.getEmployees().add(employee);
	    unit.setName(unit.getName() + "_Changed");

	    persistenceService.update(employee);
	    Unit persisted = persistenceService.find(Unit.class, unit.getId());
		ReflectionAssert.assertLenientEquals(unit, persisted);
		
		employee.setUnit(null);
		persistenceService.update(employee);
		persistenceService.delete(Unit.class, unit.getId());
		persisted = persistenceService.find(Unit.class, unit.getId());
		Assert.assertNull(persisted);
	}
	
	@Test
	public void testProjectCreation(){
		
		Project project = createProject();
		project = persistenceService.create(project);
		Project persisted = persistenceService.find(Project.class, project.getId());
		ReflectionAssert.assertLenientEquals(project, persisted);
		persistenceService.delete(Project.class, project.getId());
	}
	
	@Test
	public void testProjectDeletion(){
		
		Project project = createProject();
		project = persistenceService.create(project);
		Project persisted = persistenceService.find(Project.class, project.getId());
		ReflectionAssert.assertLenientEquals(project, persisted);
		persistenceService.delete(Project.class, project.getId());
		persisted = persistenceService.find(Project.class, project.getId());
		Assert.assertNull(persisted);
	}
	
	@Test
	public void testProjectModification(){
		
		Project project = createProject();
		project = persistenceService.create(project);
		
		Employee employee = createEmployee();
	    employee =  persistenceService.create(employee);
		
	    employee.getProjects().add(project);
	    project.getEmployees().add(employee);
	    project.setName(project.getName() + "_Changed");

	    persistenceService.update(employee);
	    Project persisted = persistenceService.find(Project.class, project.getId());
		ReflectionAssert.assertLenientEquals(project, persisted);
		
		employee.getProjects().remove(project);
		persistenceService.update(employee);
		persistenceService.delete(Project.class, project.getId());
		persisted = persistenceService.find(Project.class, project.getId());
		Assert.assertNull(persisted);
	}
}
