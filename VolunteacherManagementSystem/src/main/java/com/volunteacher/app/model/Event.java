package com.volunteacher.app.model;

import java.util.Calendar;
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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class Event {
	
	@Id
	@GeneratedValue(
	    strategy= GenerationType.AUTO,
	    generator="native"
	)
	@GenericGenerator(
	    name = "native",
	    strategy = "native"
	)
	@Column(length = 6)
	private int eventId;
	
	@NotNull
	@Column(length = 50, nullable = false, columnDefinition = "Char(50)")
	private String title;
	
	@NotNull
	@Column(nullable = false, columnDefinition = "TEXT")
	private String eventData;
	
	@NotNull
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(nullable = false, columnDefinition = "DATE")
	private Calendar eventDate;
	
	@NotNull
	@JsonFormat(pattern = "HH:mm:ss")
	@Column(nullable = false, columnDefinition = "TIME")
	private Calendar eventStartingTime;
	
	@NotNull
	@JsonFormat(pattern = "HH:mm:ss")
	@Column(nullable = false, columnDefinition = "TIME")
	private Calendar eventEndingTime;
	
	@NotNull
	@ManyToOne
	private Project project;
	
	@OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
	private List<Participant> participants;
	
	@NotNull
	@OneToOne
	private Village village;
	
	@ManyToMany
	private List<Kid> kids;
	
	@ManyToMany
	private List<Activity> activities;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "event")
	private Notification notification;

	public Event() {
		super();
	}
	
	public Event(String title, String eventData, Calendar eventDate,
			Calendar eventstartingTime,Calendar eventendingTime, Project project,  Village village,
			List<Kid> kids) {
		super();
		this.title = title;
		this.eventData = eventData;
		this.eventDate = eventDate;
		this.eventStartingTime = eventstartingTime;
		this.eventEndingTime = eventendingTime;
		this.project = project;
		this.village = village;
		this.kids = kids;
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

	public Calendar getEventStartingTime() {
		return eventStartingTime;
	}

	public void setEventStartingTime(Calendar eventStartingTime) {
		this.eventStartingTime = eventStartingTime;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public Calendar getEventEndingTime() {
		return eventEndingTime;
	}

	public void setEventEndingTime(Calendar eventEndingTime) {
		this.eventEndingTime = eventEndingTime;
	}

	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", title=" + title + ", eventData=" + eventData + ", eventDate="
				+ eventDate + ", eventStartingTime=" + eventStartingTime + ", project=" + project + ", participants="
				+ participants + ", village=" + village + ", kids=" + kids + ", notification=" + notification
				+ ", activities=" + activities + ", eventEndingTime=" + eventEndingTime + "]";
	}
}
