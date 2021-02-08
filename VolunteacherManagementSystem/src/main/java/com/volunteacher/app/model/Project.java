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
}