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
}