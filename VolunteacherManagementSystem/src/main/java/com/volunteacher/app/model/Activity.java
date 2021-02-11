package com.volunteacher.app.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;


@Entity
public class Activity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length=3)
	private int activityId;
	
	@NotNull
	@Column(nullable = false , length=20, unique = true , columnDefinition = "Char")
	private String activityName;
	
	@ManyToMany(mappedBy = "activities")
	private List<Participant> participants;
	
	//add
	@ManyToMany
	private List<Event> events;

	public Activity() {
		super();
	}
	
	public Activity(String activityName, List<Participant> participants, List<Event> events) {
		super();
		this.activityName = activityName;
		this.participants = participants;
		this.events = events;
	}
	
	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	@Override
	public String toString() {
		return "Activity [activityId=" + activityId + ", activityName=" + activityName + ", participants="
				+ participants + ", events=" + events + "]";
	}
}
