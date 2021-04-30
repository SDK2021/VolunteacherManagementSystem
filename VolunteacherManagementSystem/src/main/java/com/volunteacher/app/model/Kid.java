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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

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
	@JsonFormat(shape = Shape.STRING,pattern = "MM-dd-yyyy")
	@Column(nullable = false, columnDefinition = "DATE")
	private Calendar dob;
	
	@NotNull
	@Column(nullable = false, length = 10)
	private int standard;
	
	@NotNull
	@Column(nullable = false, columnDefinition = "TINYINT")
	private int level;
	
	private int totalSessions;
	
	@NotNull
	@Column(nullable = false, columnDefinition = "TEXT")
	private String photo;
	
	private String school;
	
	@NotNull
	@OneToOne
	private KidsGroup group;
	
	@NotNull
	@ManyToOne
	private Village village;
	
	@NotNull
	@ManyToOne
	private Area area;
	
	@ManyToMany(mappedBy = "kids")
	private List<Project> projects;
	
	@ManyToMany(mappedBy = "kids")
	private List<Event> events;
	
	@OneToMany(mappedBy = "kid")
	private List<EventKidActivity> eka;
	
	@OneToMany(mappedBy = "kid", cascade = CascadeType.REMOVE)
	private List<KidsReport> kidsReport;
	
	@ManyToMany(mappedBy = "kids",cascade = CascadeType.ALL)
	private List<Attendance> attendances;
	
	public Kid() {
		super();
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

	public int getStandard() {
		return standard;
	}

	public void setStandard(int standard) {
		this.standard = standard;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public KidsGroup getGroup() {
		return group;
	}

	public void setGroup(KidsGroup group) {
		this.group = group;
	}

	public Village getVillage() {
		return village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}

	public int getTotalSessions() {
		return totalSessions;
	}

	public void setTotalSessions(int totalSessions) {
		this.totalSessions = totalSessions;
	}
	

}
