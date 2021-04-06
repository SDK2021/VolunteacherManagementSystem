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

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;


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
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(nullable = false, columnDefinition = "DATE")
	private Calendar startingDate;
	
	@NotNull
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(nullable = false, columnDefinition = "DATE")
	private Calendar endingDate;
	
	@NotNull
	@Column(nullable = false , columnDefinition = "TEXT")
	private String projectData;
	
	@NotNull
	@CreatedDate
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(nullable = false)
	private Calendar creationDate;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
	private List<Session> sessions;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
	private List<Event> events;
	
	@NotNull
	@JsonFormat(timezone = "IST",pattern = "HH:mm:ss")
	@Column(nullable = false, columnDefinition = "TIME")
	private Calendar creationTime;

	@ManyToMany
	private List<Volunteacher> volunteachers;
	
	@ManyToMany
	private List<Kid> kids;
	

	public Project() {
		super();
	}

	public Project(String projectName,Calendar startingDate, Calendar endingDate,
			 String projectData,Calendar creationDate, List<Session> sessions, List<Event> events,
			List<Volunteacher> volunteachers, List<Kid> kids) {
		super();
		this.projectName = projectName;
		this.startingDate = startingDate;
		this.endingDate = endingDate;
		this.projectData = projectData;
		this.creationDate = creationDate;
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

	public Calendar getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Calendar creationTime) {
		this.creationTime = creationTime;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
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
				+ ", events=" + events + ", volunteachers=" + volunteachers + ", kids="
				+ kids + "]";
	}
}