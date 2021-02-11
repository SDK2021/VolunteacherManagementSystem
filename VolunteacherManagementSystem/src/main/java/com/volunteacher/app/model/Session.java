package com.volunteacher.app.model;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Session {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length=8)
	private int sessionId;
	
	//attendance	
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date sessionDate;
	
	@NotNull
	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Date startingTime;
	
	@NotNull
	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Date endingTime;
	
	@NotNull
	@CreatedDate
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date creationDate;
	
	//Many session belongs to one project
	@NotNull
	@ManyToOne
	private Project project;
			
	//One session many reports - cascade deletion
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "session")
	private List<SessionReport> sessionReports;
	
	@NotNull
	@ManyToOne
	private Village village;
	
	@ManyToMany(mappedBy = "sessions")
	private List<Volunteacher> volunteachers;
	
	@ManyToMany
	private List<Kid> kids;
	
	//add
	@OneToOne(cascade = CascadeType.ALL , mappedBy = "event")
	private Notification notification;

	public Session() {
		super();
	}

	public Session(@NotNull Date sessionDate, @NotNull Date startingTime, @NotNull Date endingTime,
			@NotNull Date creationDate, @NotNull Project project, List<SessionReport> sessionReports,
			@NotNull Village village, List<Volunteacher> volunteachers, List<Kid> kids, Notification notification) {
		super();
		this.sessionDate = sessionDate;
		this.startingTime = startingTime;
		this.endingTime = endingTime;
		this.creationDate = creationDate;
		this.project = project;
		this.sessionReports = sessionReports;
		this.village = village;
		this.volunteachers = volunteachers;
		this.kids = kids;
		this.notification = notification;
	}
	
	public int getSessionId() {
		return sessionId;
	}

	public Date getSessionDate() {
		return sessionDate;
	}

	public void setSessionDate(Date sessionDate) {
		this.sessionDate = sessionDate;
	}

	public Date getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(Date startingTime) {
		this.startingTime = startingTime;
	}

	public Date getEndingTime() {
		return endingTime;
	}

	public void setEndingTime(Date endingTime) {
		this.endingTime = endingTime;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<SessionReport> getSessionReports() {
		return sessionReports;
	}

	public void setSessionReports(List<SessionReport> sessionReports) {
		this.sessionReports = sessionReports;
	}

	public Village getVillage() {
		return village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}

	public List<Volunteacher> getVolunteachers() {
		return volunteachers;
	}

	public void setVolunteachers(List<Volunteacher> volunteachers) {
		this.volunteachers = volunteachers;
	}

	public List<Kid> getKids() {
		return kids;
	}

	public void setKids(List<Kid> kids) {
		this.kids = kids;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	@Override
	public String toString() {
		return "Session [sessionId=" + sessionId + ", sessionDate=" + sessionDate + ", startingTime=" + startingTime
				+ ", endingTime=" + endingTime + ", creationDate=" + creationDate + ", project=" + project
				+ ", sessionReports=" + sessionReports + ", village=" + village + ", volunteachers=" + volunteachers
				+ ", kids=" + kids + ", notification=" + notification + "]";
	}	
}