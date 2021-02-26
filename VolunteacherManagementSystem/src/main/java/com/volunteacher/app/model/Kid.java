package com.volunteacher.app.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Kid {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 8)
	private int kidId;
	
	@NotNull
	@Size(min = 3 , max = 20)
	@Column(nullable = false, columnDefinition = "Char", length = 20)
	private String name;
	
	@NotNull
	@Column(nullable = false, length = 1, columnDefinition = "TinyInt")
	private int gender;
	
	@NotNull
	@JsonFormat(pattern = "dd-mm-yyyy")
	@Column(nullable = false)
	private Calendar dob;
	
	@NotNull
	@Column(nullable = false, length = 2)
	private int standard;
	
	@NotNull
	@Size(min = 2 , max = 20)
	@Column(nullable = false, length = 20)
	private String area;
	
	
	private String photo;
	
	@OneToOne
	private School school;
	
	@NotNull
	@OneToOne
	private Village village;
	
	@NotNull
	@OneToOne
	private KidsGroup group;
	
	@ManyToMany(mappedBy = "kids")
	private List<Project> projects;
	
	@ManyToMany(mappedBy = "kids")
	private List<Session> sessions;
	
//	?think....
	@ManyToMany(mappedBy = "kids")
	private List<Event> events;
	
	@OneToOne(cascade = CascadeType.ALL , mappedBy = "kid")
	private KidsReport kidReport;
	
	
	public Kid() {
		super();
	}

	public Kid(@NotNull @Size(min = 3, max = 20) String name, @NotNull int gender, Calendar dob,
			@NotNull int standard, @NotNull @Size(min = 2, max = 20) String area, String photo, School school,
			@NotNull Village village, @NotNull KidsGroup group, List<Project> projects, List<Session> sessions,
			List<Event> events) {
		super();
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.standard = standard;
		this.area = area;
		this.photo = photo;
		this.school = school;
		this.village = village;
		this.group = group;
		this.projects = projects;
		this.sessions = sessions;
		this.events = events;
	}

	public int getKidId() {
		return kidId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Calendar getDob() {
		return dob;
	}

	public void setDob(Calendar dob) {
		this.dob = dob;
	}

	public int getStandard() {
		return standard;
	}

	public void setStandard(int standard) {
		this.standard = standard;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public Village getVillage() {
		return village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}

	public KidsGroup getGroup() {
		return group;
	}

	public void setGroup(KidsGroup group) {
		this.group = group;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
	public KidsReport getKidReport() {
		return kidReport;
	}

	public void setKidReport(KidsReport kidReport) {
		this.kidReport = kidReport;
	}


	@Override
	public String toString() {
		return "Kid [kidId=" + kidId + ", name=" + name + ", gender=" + gender + ", dob=" + dob + ", standard="
				+ standard + ", area=" + area + ", photo=" + photo + ", school=" + school + ", village=" + village
				+ ", group=" + group + ", projects=" + projects + ", sessions=" + sessions + ", events=" + events
				+ ", kidReport=" + kidReport + "]";
	}
}
