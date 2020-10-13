package jpa.test;

import jpa.dao.EmployeeDAO;
import jpa.dao.ProjectDAO;
import jpa.domain.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {

	
	public static void main(String[] args)
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();
		
		EmployeeDAO empDao = new EmployeeDAO(manager);
		
		Employee bob = new Employee("Bob", "Ross");
		Employee dan = new Employee("Dan", "Maven");
		empDao.insertEmployee(bob);
		empDao.insertEmployee(dan);
		
		System.out.println("List of all employees");
		System.out.println(empDao.list());
		
		Project proj = new Project();
		proj.setName("super projet");
		proj.setDescription("c'est un super projet");
		
		List<Employee> l = new ArrayList<Employee>();
		l.add(dan);
		
		proj.setEmployees(l);
		
		ProjectDAO projDao = new ProjectDAO(manager);
		projDao.insertProject(proj);
		System.out.println(projDao.list());
		

		
		
	}
}
