package com.volunteacher.app.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Project {
	
	@Id
	@GeneratedValue(
	    strategy= GenerationType.AUTO,
	    generator="native"
	)
	@GenericGenerator(
	    name = "native",
	    strategy = "native"
	)
	@Column(length = 3)
	private int projectId;

	@NotNull
	@Column(length=50, unique=true, nullable = false , columnDefinition = "Char(50)")
	private String projectName;
	
	@NotNull
	@JsonFormat(shape = Shape.STRING, pattern = "MM-dd-yyyy")
	@Column(nullable = false, columnDefinition = "DATE")
	private Calendar startingDate;
	
	@NotNull
	@JsonFormat(shape = Shape.STRING, pattern = "MM-dd-yyyy")
	@Column(nullable = false, columnDefinition = "DATE")
	private Calendar endingDate;
	
	@NotNull
	@Column(nullable = false , columnDefinition = "TEXT")
	private String projectData;
	
	@NotNull
	@Column(nullable = false, columnDefinition = "TEXT")
	private String description;
	
	@NotNull
	@CreatedDate
	@JsonFormat(shape = Shape.STRING, pattern = "MM-dd-yyyy")
	@Column(nullable = false)
	private Calendar creationDate;
	
	@NotNull
	@Column(nullable = false, columnDefinition = "TEXT")
	private String photo;
	
	@NotNull
	@JsonFormat(shape = Shape.STRING, pattern = "HH:mm:ss")
	@Column(nullable = false, columnDefinition = "TIME")
	@CreationTimestamp
	private Calendar creationTime;
	
	@Column(length = 6)
	private int totalKids;
	
	@Column(length = 6)
	private int totalSessions;
	
	@Column(length = 6)	
	private int totalVolunteachers;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
	private List<Session> sessions;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
	private List<Event> events;

	@ManyToMany
	private List<User> users;
	
	@ManyToMany
	private List<Kid> kids;
	

	public Project() {
		super();
	}

	public int getProjectId() {
		return projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Calendar getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(Calendar startingDate) {
		this.startingDate = startingDate;
	}

	public Calendar getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(Calendar endingDate) {
		this.endingDate = endingDate;
	}

	public String getProjectData() {
		return projectData;
	}

	public void setProjectData(String projectData) {
		this.projectData = projectData;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Calendar getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Calendar creationTime) {
		this.creationTime = creationTime;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public int getTotalKids() {
		return totalKids;
	}

	public void setTotalKids(int totalKids) {
		this.totalKids = totalKids;
	}

	public int getTotalSessions() {
		return totalSessions;
	}

	public void setTotalSessions(int totalSessions) {
		this.totalSessions = totalSessions;
	}

	public int getTotalVolunteachers() {
		return totalVolunteachers;
	}

	public void setTotalVolunteachers(int totalVolunteacher) {
		this.totalVolunteachers = totalVolunteacher;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Kid> getKids() {
		return kids;
	}
	
	public void setKids(List<Kid> kids) {
		this.kids = kids;
	}
}