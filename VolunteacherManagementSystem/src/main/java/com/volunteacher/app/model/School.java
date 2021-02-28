package com.volunteacher.app.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class School {
	
	@Id
	@Column(length = 3)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int schoolId;
	
	@NotNull
	@Size(min = 2 , max = 30)
	@Column(nullable = false, length = 30)
	private String name;
	
	@Column(length = 6)
	private int pincode;
	
	@Column(length = 5)
	private int totalLabs;
	
	@NotNull
	@Column(nullable = false, length = 10, unique = true)
	private String phoneNumber;
	
	@JsonFormat(pattern = "dd-mm-yyyy")
	private Calendar startingDate;
	
	@NotNull
	@Size(min = 2 , max = 20)
	@Column(nullable = false, length = 20)
	private String stream;
	
	@NotNull
	@Column(nullable = false, length = 4)
	private int totalStudent;
	
	@NotNull
	@Column(nullable = false, length = 1, columnDefinition = "TinyInt") 
	private int status;
	
	@ManyToMany(mappedBy = "schools")
	private List<Requirement> requirements;
	
	@OneToOne
	Village village;

	
	public School() {
		super();
	}

	public School(@NotNull @Size(min = 2, max = 30) String name, int pincode, int totalLabs,
			@NotNull String phoneNumber, Calendar startingDate, @NotNull @Size(min = 2, max = 20) String stream,
			@NotNull int totalStudent, @NotNull int status, List<Requirement> requirements, Village village) {
		super();
		this.name = name;
		this.pincode = pincode;
		this.totalLabs = totalLabs;
		this.phoneNumber = phoneNumber;
		this.startingDate = startingDate;
		this.stream = stream;
		this.totalStudent = totalStudent;
		this.status = status;
		this.requirements = requirements;
		this.village = village;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<Requirement> getRequirements() {
		return requirements;
	}

	public void setRequirements(List<Requirement> requirements) {
		this.requirements = requirements;
	}

	public Village getVillage() {
		return village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}

	@Override
	public String toString() {
		return "School [id=" + schoolId + ", name=" + name + ", pincode=" + pincode + ", totalLabs=" + totalLabs
				+ ", phoneNumber=" + phoneNumber + ", startingDate=" + startingDate + ", stream=" + stream
				+ ", totalStudent=" + totalStudent + ", status=" + status + ", requirements=" + requirements
				+ ", village=" + village + "]";
	}
}
