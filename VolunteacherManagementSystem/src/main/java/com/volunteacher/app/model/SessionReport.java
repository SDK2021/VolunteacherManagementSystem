package com.volunteacher.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class SessionReport {
	
	public SessionReport() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(length=10)
	private long sessionReportId;
	
	@NotNull
	@Column(nullable = false , columnDefinition = "TEXT")
	private String description;
	
	@NotNull
	@Column(nullable = false , columnDefinition = "TEXT")
	private String suggestions;
	
	@NotNull
	@Column(length = 20, nullable = false)
	private String experience;
	
	@NotNull
	@Column(nullable = false , columnDefinition = "TEXT")
	private String requirements;
	
	@NotNull
	@ManyToOne
	private Session session;
		
	@NotNull
	@ManyToOne
	private User user;
	

	public SessionReport(String description, String suggestions,
			 String experience, String requirements, Session session, User user) {
		super();
		this.description = description;
		this.suggestions = suggestions;
		this.experience = experience;
		this.requirements = requirements;
		this.session = session;
		this.user = user;
	}

	public long getSessionReportId() {
		return sessionReportId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(String suggestions) {
		this.suggestions = suggestions;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "SessionReport [sessionReportId=" + sessionReportId + ", description=" + description + ", suggestions="
				+ suggestions + ", experience=" + experience + ", requirements=" + requirements + ", session=" + session
				+ ", user=" + user + "]";
	}
}
