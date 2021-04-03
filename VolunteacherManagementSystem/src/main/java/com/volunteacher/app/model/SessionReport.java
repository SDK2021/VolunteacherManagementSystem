package com.volunteacher.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class SessionReport {
	
	@Id
	@GeneratedValue(
	    strategy= GenerationType.AUTO,
	    generator="native"
	)
	@GenericGenerator(
	    name = "native",
	    strategy = "native"
	)
	@Column(length=8)
	private int sessionReportId;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@Column(columnDefinition = "TEXT")
	private String suggestions;
	
	@NotNull
	@Column(length = 20, nullable = false, columnDefinition = "TEXT")
	private String experience;
	
	@Column(columnDefinition = "TEXT")
	private String requirements;
	
	@NotNull
	@ManyToOne
	private Session session;
		
	@NotNull
	@ManyToOne
	private User user;
	
	public SessionReport() {
		super();
	}

	public SessionReport(String description, String suggestions,
			String experience,String requirements, Session session,User user) {
		super();
		this.description = description;
		this.suggestions = suggestions;
		this.experience = experience;
		this.requirements = requirements;
		this.session = session;
		this.user = user;
	}

	public int getSessionReportId() {
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