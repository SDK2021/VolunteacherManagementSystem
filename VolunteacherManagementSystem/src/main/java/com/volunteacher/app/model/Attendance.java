package com.volunteacher.app.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Attendance {
	
	@Id
	@GeneratedValue(
	    strategy= GenerationType.AUTO,
	    generator="native"
	)
	@GenericGenerator(
	    name = "native",
	    strategy = "native"
	)
	@Column(length=10)
	private Long attendanceId;
	
	@NotNull
	@ManyToOne
	private Session session; 
	
	@OneToOne
	@NotNull
	private KidsGroup group;

	@ManyToMany(mappedBy = "attendances")
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
