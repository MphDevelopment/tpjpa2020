package jpa.domain;

import java.sql.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Task {

	private Project project;
	private int id;
	private String name;
	private Date limitDate;
	private Employee employee;
	private int estimatedDuration;
	private List<Tag> tags;
	private String location;
	private String url;
	private String description;
	private String category; //todo, doing, done

	public Task(Project project, String name, String description, Date limitDate, Employee employee,
			int estimatedDuration, String location, String url, String category) {
		this.project = project;
		this.name = name;
		this.description = description;
		this.limitDate = limitDate;
		this.employee = employee;
		this.estimatedDuration = estimatedDuration;
		this.location = location;
		this.url = url;
		this.category = category;
	}
	
	public Task() {
		
	}
	
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@ManyToOne(cascade=CascadeType.REMOVE)
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

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

	public Date getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}

	@ManyToOne(cascade=CascadeType.REMOVE)
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public int getEstimatedDuration() {
		return estimatedDuration;
	}

	public void setEstimatedDuration(int estimatedDuration) {
		this.estimatedDuration = estimatedDuration;
	}

	@ManyToMany(cascade=CascadeType.REMOVE)
	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
