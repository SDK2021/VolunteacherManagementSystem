package com.volunteacher.app.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;



@Entity
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length=6)
	private int eventId;
	
	@NotNull
	@Column(length = 50, nullable = false , columnDefinition = "Char")
	private String title;
	
	@NotNull
	@Column(nullable = false , columnDefinition = "TEXT")
	private String eventData;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date eventDate;
	
	@NotNull
	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Date eventTime;
	
	//Many events belongs to one project
	@ManyToOne
	private Project project;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
	private List<Participant> participants;
	
	@ManyToOne
	private Village village;
	
	@ManyToMany
	private List<Kid> kids;
	
	//Kids Event participants activity kids?	
}