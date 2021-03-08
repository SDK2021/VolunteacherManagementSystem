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
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(length=10)
	private Long attendanceId;
	
	@NotNull
	@OneToOne
	private Session session; 
	
	@OneToOne
	@NotNull
	private KidsGroup group;

	@OneToMany
	@NotNull
	private List<Kid> kids;

	public Attendance() {
		super();
	}

	public Attendance(Session session, KidsGroup group, List<Kid> kids) {
		super();
		this.session = session;
		this.group = group;
		this.kids = kids;
	}

	public Long getAttendanceId() {
		return attendanceId;
	}
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public KidsGroup getGroups() {
		return group;
	}

	public void setGroups(KidsGroup groups) {
		this.group = groups;
	}
	
	public KidsGroup getGroup() {
		return group;
	}

	public void setGroup(KidsGroup group) {
		this.group = group;
	}

	public List<Kid> getKids() {
		return kids;
	}

	public void setKids(List<Kid> kids) {
		this.kids = kids;
	}

	@Override
	public String toString() {
		return "Attendance [attendanceId=" + attendanceId + ", session=" + session + ", group=" + group + ", kids="
				+ kids + "]";
	}
	
}
