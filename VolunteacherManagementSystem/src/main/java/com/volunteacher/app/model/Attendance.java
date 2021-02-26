package com.volunteacher.app.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Attendance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length=10)
	private int attendanceId;
	
	@NotNull
	@OneToOne
	private Session session; 
	
	@OneToMany
	private List<KidsGroup> groups;

	public Attendance() {
		super();
	}

	public Attendance(Session session, List<KidsGroup> groups) {
		super();
		this.session = session;
		this.groups = groups;
	}

	public int getAttendanceId() {
		return attendanceId;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public List<KidsGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<KidsGroup> groups) {
		this.groups = groups;
	}
	
	@Override
	public String toString() {
		return "Attendance [attendanceId=" + attendanceId + ", session=" + session + ", groups=" + groups + "]";
	}
	
}
