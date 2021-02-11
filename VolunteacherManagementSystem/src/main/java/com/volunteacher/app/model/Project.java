package com.volunteacher.app.model;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 3)
	private int projectId;

	@NotNull
	@Column(length=50, unique=true, nullable = false , columnDefinition = "Char")
	private String projectName;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date startingDate;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date endingDate;
	
	@NotNull
	@Column(nullable = false , columnDefinition = "TEXT")
	private String projectData;
	
	@NotNull
	@CreatedDate
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date creationDate;
	
	//One Project Many Sessions - cascade deletion
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
	private List<Session> sessions;
	
	//One project many events
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
	private List<Event> events;
	
	//Does cascading required?
	@ManyToMany(mappedBy = "projects")
	private List<Volunteacher> volunteachers;
	
	@ManyToMany
	private List<Kid> kids;
	

	public Project() {
		super();
	}

	public Project(@NotNull String projectName, @NotNull Date startingDate, @NotNull Date endingDate,
			@NotNull String projectData, @NotNull Date creationDate, List<Session> sessions, List<Event> events,
			List<Volunteacher> volunteachers, List<Kid> kids) {
		super();
		this.projectName = projectName;
		this.startingDate = startingDate;
		this.endingDate = endingDate;
		this.projectData = projectData;
		this.creationDate = creationDate;
		this.sessions = sessions;
		this.events = events;
		this.volunteachers = volunteachers;
		this.kids = kids;
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

	public Date getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}

	public Date getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}

	public String getProjectData() {
		return projectData;
	}

	public void setProjectData(String projectData) {
		this.projectData = projectData;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public List<Volunteacher> getVolunteachers() {
		return volunteachers;
	}

	public void setVolunteachers(List<Volunteacher> volunteachers) {
		this.volunteachers = volunteachers;
	}

	public List<Kid> getKids() {
		return kids;
	}

	public void setKids(List<Kid> kids) {
		this.kids = kids;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", startingDate=" + startingDate
				+ ", endingDate=" + endingDate + ", projectData=" + projectData + ", creationDate=" + creationDate
				+ ", sessions=" + sessions + ", events=" + events + ", volunteachers=" + volunteachers + ", kids="
				+ kids + "]";
	}
}