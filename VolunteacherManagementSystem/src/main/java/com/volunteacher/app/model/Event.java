package com.volunteacher.app.model;

import java.util.Calendar;
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

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 6)
	private int eventId;
	
	@NotNull
	@Column(length = 50, nullable = false, columnDefinition = "Char")
	private String title;
	
	@NotNull
	@Column(nullable = false, columnDefinition = "TEXT")
	private String eventData;
	
	@NotNull
	@JsonFormat(pattern = "dd-mm-yyyy")
	@Column(nullable = false)
	private Calendar eventDate;
	
	@NotNull
	@JsonFormat(pattern = "HH:mm:ss")
	@Column(nullable = false)
	private Calendar eventTime;
	
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
	@OneToOne(cascade = CascadeType.ALL , mappedBy = "event")
	private Notification notification;
	
	//add
	@ManyToMany(mappedBy = "events")
	private List<Activity> activities;
	//Kids Event participants activity kids?
	//New table or not
	

	public Event() {
		super();
	}
	
	public Event(String title, String eventData, Calendar eventDate,
			Calendar eventTime, Project project, List<Participant> participants, Village village,
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

	public Calendar getEventDate() {
		return eventDate;
	}

	public void setEventDate(Calendar eventDate) {
		this.eventDate = eventDate;
	}

	public Calendar getEventTime() {
		return eventTime;
	}

	public void setEventTime(Calendar eventTime) {
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

	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", title=" + title + ", eventData=" + eventData + ", eventDate="
				+ eventDate + ", eventTime=" + eventTime + ", project=" + project + ", participants=" + participants
				+ ", village=" + village + ", kids=" + kids + ", notification=" + notification + ", activities="
				+ activities + "]";
	}	
}
