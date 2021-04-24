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
import com.fasterxml.jackson.annotation.JsonFormat.Shape;


@Entity
//@IdClass(Event.class)
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
	@JsonFormat(shape = Shape.STRING,pattern = "MM-dd-yyyy")
	@Column(nullable = false, columnDefinition = "DATE")
	private Calendar eventDate;
	
	@NotNull
	@JsonFormat(shape = Shape.STRING,pattern = "HH:mm:ss")
	@Column(nullable = false, columnDefinition = "TIME")
	private Calendar eventStartingTime;
	
	@NotNull
	@JsonFormat(shape = Shape.STRING, pattern = "HH:mm:ss")
	@Column(nullable = false, columnDefinition = "TIME")
	private Calendar eventEndingTime;
	
	@Column(columnDefinition = "TEXT")
	private String photo;
	
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
	
	@OneToMany(mappedBy = "event")
	private List<EventKidActivity> eka;
	
	@ManyToMany
	private List<Activity> activities;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "event")
	private Notification notification;

	public Event() {
		super();
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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setEventEndingTime(Calendar eventEndingTime) {
		this.eventEndingTime = eventEndingTime;
	}
}
