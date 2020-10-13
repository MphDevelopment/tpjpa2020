package jpa.dao;

import java.lang.module.FindException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import jpa.domain.Task;

public class TaskDAO {

	private EntityManager manager;

	public TaskDAO(EntityManager manager) {
		this.manager = manager;
	}

	public void insertTask(Task task) {
		EntityTransaction tx = manager.getTransaction();
		try {
			tx.begin();
			manager.persist(task);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}

	public void deleteTask(Task task) {
		EntityTransaction entityTransaction = manager.getTransaction();
		try {
			entityTransaction.begin();
			manager.remove(task);
			entityTransaction.commit();
			System.out.println("Task deleted");
		} catch (Exception e) {
			entityTransaction.rollback();
			e.printStackTrace();
			System.out.println("Task not found");
		}
	}

	public void changeCategory(Integer id) {

		String category = (String) manager.createQuery("SELECT t.category from Task where t.id = :id")
				.setParameter("id", id).getSingleResult();

		EntityTransaction tx = manager.getTransaction();

		switch (category) {
		case "todo":
			tx.begin();
			manager.createQuery("update Task t set t.category = 'doing' where id = :id").setParameter("id", id)
					.executeUpdate();
			return;
		case "doing":
			tx.begin();
			manager.createQuery("update Task t set t.category = 'done' where id = :id").setParameter("id", id)
					.executeUpdate();
			return;
		}
	}

}
