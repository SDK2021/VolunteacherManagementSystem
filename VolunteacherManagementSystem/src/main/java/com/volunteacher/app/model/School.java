package com.volunteacher.app.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
public class School {
	
	@Id
	@GeneratedValue(
	    strategy= GenerationType.AUTO,
	    generator="native"
	)
	@GenericGenerator(
	    name = "native",
	    strategy = "native"
	)
	@Column(length = 3)
	private int schoolId;
	
	@NotNull
	@Size(min = 2 , max = 30)
	@Column(nullable = false, length = 30, columnDefinition = "Char(30)")
	private String schoolName;
	
	@Column(length = 6)
	private int pincode;
	
	@Column(length = 5)
	private int totalLabs;
	
	@NotNull
	@Column(nullable = false, length = 10, unique = true)
	private String phoneNumber;
	
	@JsonFormat(shape = Shape.STRING,pattern = "MM-dd-yyyy")
	@Column(columnDefinition = "DATE")
	private Calendar startingDate;
	
	@NotNull
	@Column(nullable = false, length = 30)
	private String stream;
	
	@NotNull
	@Column(nullable = false, length = 4)
	private int totalStudent;
	
	@NotNull
	@Column(nullable = false, length = 1, columnDefinition = "TinyInt") 
	private int status;
	
	@Column(columnDefinition = "TEXT")
	private String requirements;
	
	@OneToOne
	private Village village;

	
	public School() {
		super();
	}

	public int getSchoolId() {
		return schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public int getTotalLabs() {
		return totalLabs;
	}

	public void setTotalLabs(int totalLabs) {
		this.totalLabs = totalLabs;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Calendar getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(Calendar startingDate) {
		this.startingDate = startingDate;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public int getTotalStudent() {
		return totalStudent;
	}

	public void setTotalStudent(int totalStudent) {
		this.totalStudent = totalStudent;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Village getVillage() {
		return village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}
	
	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}
}
