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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;



@Entity
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 6)
	private int eventId;
	
	@NotNull
	@Column(length = 50, nullable = false, columnDefinition = "Char")
	private String title;
	
	@NotNull
	@Column(nullable = false, columnDefinition = "TEXT")
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
	@NotNull
	@ManyToOne
	private Project project;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
	private List<Participant> participants;
	
	@NotNull
	@ManyToOne
	private Village village;
	
	@ManyToMany
	private List<Kid> kids;
	
	//add
	@OneToOne(cascade = CascadeType.ALL , mappedBy = "session")
	private Notification notification;
	
	//add
	@ManyToMany(mappedBy = "events")
	private List<Activity> activities;
	//Kids Event participants activity kids?
	//New table or not
	

	public Event() {
		super();
	}
	
	public Event(@NotNull String title, @NotNull String eventData, @NotNull Date eventDate,
			@NotNull Date eventTime, @NotNull Project project, List<Participant> participants, @NotNull Village village,
			List<Kid> kids, Notification notification, List<Activity> activities) {
		super();
		this.title = title;
		this.eventData = eventData;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.project = project;
		this.participants = participants;
		this.village = village;
		this.kids = kids;
		this.notification = notification;
		this.activities = activities;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEventData() {
		return eventData;
	}

	public void setEventData(String eventData) {
		this.eventData = eventData;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Date getEventTime() {
		return eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	public Village getVillage() {
		return village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}

	public List<Kid> getKids() {
		return kids;
	}

	public void setKids(List<Kid> kids) {
		this.kids = kids;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}	
}