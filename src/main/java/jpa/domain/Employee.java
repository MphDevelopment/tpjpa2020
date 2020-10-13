package jpa.domain;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@NamedQueries(@NamedQuery(name="all", query="select e from Employee e"))
public class Employee {

	private int id;
	private String firstName;
	private String lastName;
	private List<Project> projects;
	private List<Task> tasks;
	
	
	public Employee(String firstName, String lastName)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.projects = new ArrayList<Project>();
	}
	
	public Employee(String firstName, String lastName, List<Project> projects, List<Task> tasks)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.projects = projects;
		this.tasks=tasks;
	}
	
	public Employee() {
		
	}
	
	@OneToMany(cascade=CascadeType.REMOVE)
	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@ManyToMany(cascade=CascadeType.REMOVE)
	public List<Project> getProjects() {
		return projects;
	}
	
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	@Override
	public String toString()
	{
		return String.format("Employee[id=%d, name=%s %s]", this.id, firstName, (String)lastName.toUpperCase());
	}
	
	
}
