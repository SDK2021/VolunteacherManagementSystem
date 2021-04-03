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

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Kid {
	
	@Id
	@GeneratedValue(
	    strategy= GenerationType.AUTO,
	    generator="native"
	)
	@GenericGenerator(
	    name = "native",
	    strategy = "native"
	)
	@Column(length = 8)
	private Long kidId;
	
	@NotNull
	@Size(min = 3 , max = 20)
	@Column(nullable = false, columnDefinition = "Char(20)", length = 20)
	private String name;

	@NotNull
	@Column(nullable = false, length = 1, columnDefinition = "TinyInt")
	private int gender;
	
	@NotNull
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(nullable = false, columnDefinition = "DATE")
	private Calendar dob;
	
	@NotNull
	@Column(nullable = false, length = 10)
	private String standard;
	
	@NotNull
	@Size(min = 2 , max = 20)
	@Column(nullable = false, length = 20)
	private String area;
	
	@NotNull
	@Column(nullable = false, length = 25)
	private String level;
	
	private String photo;
	
	@OneToOne
	private School school;
	
	@NotNull
	@OneToOne
	private Village village;
	
	@NotNull
	@OneToOne
	private KidsGroup group;
	
	@OneToOne(mappedBy = "kid", cascade = CascadeType.REMOVE)
	private KidsReport kidsReport;
	
	@ManyToMany(mappedBy = "kids")
	private List<Project> projects;
	
//	@ManyToMany(mappedBy = "kids")
//	private List<Session> sessions;
	
	@ManyToMany(mappedBy = "kids")
	private List<Event> events;
	
	@ManyToMany
	private List<Attendance> attendances;
	
	public Kid() {
		super();
	}

	public Kid(String name, int gender, Calendar dob,String standard, String area, String level,
			String photo, School school, Village village, KidsGroup group, KidsReport kidsReport,
			List<Project> projects, List<Event> events, List<Attendance> attendances) {
		super();
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.standard = standard;
		this.area = area;
		this.level = level;
		this.photo = photo;
		this.school = school;
		this.village = village;
		this.group = group;
		this.kidsReport = kidsReport;
		this.projects = projects;
		this.events = events;
		this.attendances = attendances;
	}

	public Long getKidId() {
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

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
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

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public List<Attendance> getAttendances() {
		return attendances;
	}

	public void setAttendances(List<Attendance> attendances) {
		this.attendances = attendances;
	}

	
}
