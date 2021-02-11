package com.volunteacher.app.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Participant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length=8)
	private int participantId;
	
	@NotNull
	@Column(length=20, nullable = false , columnDefinition = "Char")
	private String name;
	
	@NotNull
	@Column(length=40, unique=true, nullable = false)
	private String email;
	
	@NotNull
	@Column(length=1, nullable = false , columnDefinition = "TinyInt")
	private int gender;
	
	@NotNull
	@Column(length=10, unique=true, nullable = false)
	private String phoneNumber;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dob;
	
	@NotNull
	@OneToOne
	private UserType type;
	
	@NotNull
	@ManyToOne
	private Event event;
	
	@ManyToMany
	private List<Activity> activities;
	
	//participant can be user, if we delete user, though user is not there, but we can see who participated
	@NotNull
	@OneToOne
	private User user;
	

	public Participant() {
		super();
	}

	public Participant(int participantId, @NotNull String name, @NotNull String email, @NotNull int gender,
			@NotNull String phoneNumber, @NotNull Date dob, @NotNull UserType type, @NotNull Event event,
			List<Activity> activities, @NotNull User user) {
		super();
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.dob = dob;
		this.type = type;
		this.event = event;
		this.activities = activities;
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Participant [participantId=" + participantId + ", name=" + name + ", email=" + email + ", gender="
				+ gender + ", phoneNumber=" + phoneNumber + ", dob=" + dob + ", type=" + type + ", event=" + event
				+ ", activities=" + activities + ", user=" + user + "]";
	}
}