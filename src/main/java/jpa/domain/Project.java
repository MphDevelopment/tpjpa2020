package jpa.domain;

import java.util.List;

import javax.persistence.*;

@Entity
public class Project {
	
	private int id;
	private String name;
	private String description;
	private List<Employee> employees;
	private List<Task> tasks;
	
	

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@ManyToMany(cascade=CascadeType.REMOVE)
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	@OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	@Override
	public String toString()
	{
		return String.format("Project[id=%d, name=%s]", this.id, this.name);
	}
}
