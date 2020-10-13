package jpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;
import jpa.domain.Employee;
import jpa.domain.Task;
import jpa.domain.Project;

public class EmployeeDAO {

	private EntityManager manager;
	
	//todo load manager from helper 
	//so we don't have to create the manager in servlets to instantiate
	//the DAO object
	public EmployeeDAO(EntityManager manager) {
		this.manager = manager;
	}
	
	public void insertEmployee(Employee employee) {
		EntityTransaction tx = manager.getTransaction();
		try
		{
			tx.begin();
			manager.persist(employee);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}
	
	public void deleteEmployee(Employee employee) {
        EntityTransaction entityTransaction = manager.getTransaction();
        try {
            entityTransaction.begin();
            manager.remove(employee);
            entityTransaction.commit();
            System.out.println("Employee deleted");
        } catch (Exception e) {
            entityTransaction.rollback();
            e.printStackTrace();
            System.out.println("Employee not found");
        }
    }
	
	public void deleteEmployeeById(int id) {
		Employee emp = this.findEmployeeById(id);
		if(emp != null)
		{
			this.deleteEmployee(emp);
		}
	}
	
	public Employee findEmployeeById(int id){
		List<Employee> list = manager.createQuery("SELECT e from Employee e WHERE id = :id")
        .setParameter("id", id)
        .getResultList();
		
		if (list.size() > 0)
		{
			return list.get(0);
		}
		
		return null;
	}
	
	public List<Employee> findByFirstName(String str)
	{
		return manager.createQuery("SELECT e from Employee e WHERE firstName = :str")
                .setParameter("str", str)
                .getResultList();
	}
	
	public List<Employee> findByLastName(String str)
	{
		return manager.createQuery("SELECT e from Employee e WHERE lastName = :str")
                .setParameter("str", str)
                .getResultList();
	}
	
	public List<Employee> list()
	{
		return manager.createNamedQuery("all").getResultList();
	}
	
	public static void main(String[] args)
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();
		EmployeeDAO dao = new EmployeeDAO(manager);
	
		System.out.println(dao.findEmployeeById(3));
		
		dao.deleteEmployeeById(13);
		dao.deleteEmployeeById(14);
		
		System.out.println(manager.createQuery("SELECT e from Employee e ")
        .getResultList());
		
		System.out.println("DAO DONE");
	}
}
