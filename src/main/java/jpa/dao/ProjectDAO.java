package jpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.List;
import jpa.domain.Employee;
import jpa.domain.Task;
import jpa.domain.Project;

public class ProjectDAO {
	EntityManager manager;
	
	public ProjectDAO(EntityManager manager)
	{
		this.manager = manager;
	}
	
	public void insertProject(Project project)
	{
		EntityTransaction tx = manager.getTransaction();
		try
		{
			tx.begin();
			manager.persist(project);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}
	
	public void deleteProject(Project project)
	{
		EntityTransaction tx = manager.getTransaction();
		try {
            tx.begin();
            manager.remove(project);
            tx.commit();
            System.out.println("project deleted");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            System.out.println("project not found");
        }
	}
	
	public void deleteProjectById(int id)
	{
		Project project = this.findProjectById(id);
		System.out.println("Del by id : " + project);
		if(project != null)
		{
			this.deleteProject(project);
		}
	}
	
	public Project findProjectById(int id)
	{
		List<Project> result = manager.createQuery("SELECT p from Project p where id = :id")
				.setParameter("id", id)
				.getResultList();
		return result.size() != 0 ? result.get(0) : null;
	}
	
	public List<Project> findProjectsByName(String name)
	{
		return manager.createQuery("SELECT p FROM Project p WHERE name = :name")
				.setParameter("name", name)
				.getResultList();
	}
	
	public List<Project> list()
	{
		return manager.createQuery("SELECT p FROM Project p")
				.getResultList();
	}
	
	public static void main(String[] args)
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();
		ProjectDAO dao = new ProjectDAO(manager);
		
		dao.deleteProjectById(10);
		
		
		List a = dao.findProjectsByName("Projet1");
		System.out.println(a);
	}
	
	
}
