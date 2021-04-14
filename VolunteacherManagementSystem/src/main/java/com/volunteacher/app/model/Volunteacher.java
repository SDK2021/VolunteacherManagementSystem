package com.volunteacher.app.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Volunteacher {
	
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
	private int volunteacherId;

	@Column(length = 50)
	private String school;

	@Column(length = 20	)
	private String employerName;
	
	@NotNull
	@Column(length = 1, nullable = false, columnDefinition = "TINYINT")
	private int status;
	
	@NotNull
	@JsonFormat(pattern = "dd-MM-yyy")
	@CreatedDate
	@Column(nullable = false,columnDefinition = "DATE")
	private Calendar joiningDate;
	
	@NotNull
	@Column(nullable = false, columnDefinition = "TIME")
	@JsonFormat(pattern = "HH-mm-ss")
	@CreationTimestamp
	private Calendar JoiningTime;
	
	@JsonFormat(pattern = "dd-MM-yyy")
	@Column(columnDefinition = "DATE")
	private Calendar endingDate;

	@Column(length = 6, nullable = false)
	private int pincode;

	@Column(length = 20, nullable = false)
	private String education;
	
	@OneToOne
	private User user;
	
	@NotNull
	@ManyToOne
	private Village village;
	
	@NotNull
	@OneToOne
	private District district;

	public Volunteacher() {
		super();
	}

	public Volunteacher( String school, String employerName, int status,
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
	}

	public int getVolunteacherId() {
		return volunteacherId;
	}
	
	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
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

	public Calendar getJoiningTime() {
		return JoiningTime;
	}

	public void setJoiningTime(Calendar joiningTime) {
		JoiningTime = joiningTime;
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

	@Override
	public String toString() {
		return "Volunteacher [volunteacherId=" + volunteacherId + ", school=" + school + ", employerName="
				+ employerName + ", status=" + status + ", joiningDate=" + joiningDate + ", endingDate=" + endingDate
				+ ", pincode=" + pincode + ", education=" + education + ", user=" + user + ", village=" + village
				+ ", district=" + district + "]";
	}
}
