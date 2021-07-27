package com.volunteacher.app.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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
	

	@ManyToOne
	private Session session; 
	
	@ManyToOne
	private KidsGroup kidsGroup;

	@ManyToMany
	private List<Kid> kids;

	public Attendance() {
		super();
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


	public KidsGroup getKidsGroup() {
		return kidsGroup;
	}


	public void setKidsGroup(KidsGroup kidsGroup) {
		this.kidsGroup = kidsGroup;
	}


	public List<Kid> getKids() {
		return kids;
	}

	public void setKids(List<Kid> kids) {
		this.kids = kids;
	}
}
