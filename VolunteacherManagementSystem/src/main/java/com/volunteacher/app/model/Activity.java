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
	@Column(nullable = false , length=20, columnDefinition = "Char(20)")
	private String activityName;
	
	@Column(nullable = false, columnDefinition = "TEXT")
	private String description;
	
	@ManyToMany(mappedBy = "activities")
	private List<Participant> participants;
	
	@ManyToMany(mappedBy = "activities")
	private List<Event> events;
	
	public Activity() {
		super();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
