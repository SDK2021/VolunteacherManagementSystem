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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Volunteacher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length=8)
	private int volunteacherId;

	@NotNull
	@OneToOne
	private School school;

	@NotNull
	@Column(length = 20, nullable = false)
	private String employerName;
	
	@NotNull
	@Column(length = 1, nullable = false, columnDefinition = "TINYINT")
	private int status;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@CreatedDate
	@Column(nullable = false)
	private Calendar joiningDate;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Calendar endingDate;

	@NotNull
	@Column(length = 6, nullable = false)
	private int pincode;

	@NotNull
	@Column(length = 20, nullable = false)
	private String education;
	
	//One User-- for password
	@OneToOne
	private User user;
	
	@NotNull
	@ManyToOne
	private Village village;
	
	@NotNull
	@ManyToOne
	private District district;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Session> sessions;
	
	//Does cascading required?
	@ManyToMany
	private List<Project> projects;
	

	public Volunteacher() {
		super();
	}

	public Volunteacher( School school, String employerName, int status,
			Calendar joiningDate, Calendar endingDate, int pincode, String education,
			User user, Village village, District district, List<Session> sessions,
			List<Project> projects) {
		super();
		this.school = school;
		this.employerName = employerName;
		this.status = status;
		this.joiningDate = joiningDate;
		this.endingDate = endingDate;
		this.pincode = pincode;
		this.education = education;
		this.user = user;
		this.village = village;
		this.district = district;
		this.sessions = sessions;
		this.projects = projects;
	}
	
	public int getVolunteacherId() {
		return volunteacherId;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Calendar getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Calendar joiningDate) {
		this.joiningDate = joiningDate;
	}

	public Calendar getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(Calendar endingDate) {
		this.endingDate = endingDate;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Village getVillage() {
		return village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return "Volunteacher [volunteacherId=" + volunteacherId + ", school=" + school + ", employerName="
				+ employerName + ", status=" + status + ", joiningDate=" + joiningDate + ", endingDate=" + endingDate
				+ ", pincode=" + pincode + ", education=" + education + ", user=" + user + ", village=" + village
				+ ", district=" + district + ", sessions=" + sessions + ", projects=" + projects + "]";
	}
}

