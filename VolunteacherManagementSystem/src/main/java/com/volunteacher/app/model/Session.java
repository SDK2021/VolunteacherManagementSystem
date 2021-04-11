package com.volunteacher.app.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Session {
	
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
	private Long sessionId;
	
	//attendance	
	
	@NotNull
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(nullable = false, columnDefinition = "DATE")
	private Calendar sessionDate;
	
	@NotNull
	@JsonFormat(timezone = "IST",pattern = "HH:mm:ss")
	@Column(nullable = false, columnDefinition = "TIME")
	private Calendar startingTime;
	
	@NotNull
	@JsonFormat(timezone = "IST",pattern = "HH:mm:ss")
	@Column(nullable = false, columnDefinition = "TIME")
	private Calendar endingTime;
	
	@NotNull
	@CreatedDate
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(nullable = false, columnDefinition = "DATE")
	private Calendar creationDate;

	@ManyToOne
	private Project project;
	
	@OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
	private List<Attendance> attendance;
	
	@OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
	private List<SessionReport> sessionReports;
	
	@OneToOne
	private Village village;
	
	@ManyToMany
	private List<User> users;

	
	public Session() {
		super();
	}

	public Session(Calendar sessionDate, Calendar startingTime, Calendar endingTime,
			Calendar creationDate,  Project project,Village village, List<Volunteacher> volunteachers, List<Kid> kids) {
		super();
		this.sessionDate = sessionDate;
		this.startingTime = startingTime;
		this.endingTime = endingTime;
		this.creationDate = creationDate;
		this.project = project;
		this.village = village;
	}
	
	public Long getSessionId() {
		return sessionId;
	}

	public Calendar getSessionDate() {
		return sessionDate;
	}

	public void setSessionDate(Calendar sessionDate) {
		this.sessionDate = sessionDate;
	}

	public Calendar getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(Calendar startingTime) {
		this.startingTime = startingTime;
	}

	public Calendar getEndingTime() {
		return endingTime;
	}

	public void setEndingTime(Calendar endingTime) {
		this.endingTime = endingTime;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Session [sessionId=" + sessionId + ", sessionDate=" + sessionDate + ", startingTime=" + startingTime
				+ ", endingTime=" + endingTime + ", creationDate=" + creationDate + ", project=" + project
				+ ", attendance=" + attendance + ", sessionReports=" + sessionReports + ", village=" + village
				+ ", users=" + users + "]";
	}
}
