package com.volunteacher.app.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class Activity {
	
	@Id
	@GeneratedValue(
	    strategy= GenerationType.AUTO,
	    generator="native"
	)
	@GenericGenerator(
	    name = "native",
	    strategy = "native"
	)
	@Column(length=3)
	private int activityId;
	
	@NotNull
	@Column(nullable = false , length=20, unique = true , columnDefinition = "Char(20)")
	private String activityName;
	
	@ManyToMany(mappedBy = "activities")
	private List<Participant> participants;
	
	//add
	@ManyToMany(mappedBy = "activities")
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
	
	public int getActivityId() {
		return activityId;
	}
	
	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	@Override
	public String toString() {
		return "Activity [activityId=" + activityId + ", activityName=" + activityName + ", participants="
				+ participants + ", events=" + events + "]";
	}
}
