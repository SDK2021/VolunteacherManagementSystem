package com.volunteacher.app.model;

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

@Entity
public class Kid {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 8)
	private int kidId;
	
	@NotNull
	@Size(min = 3 , max = 20)
	@Column(nullable = false , columnDefinition = "Char" , length = 20)
	private String name;
	
	@NotNull
	@Column(nullable = false , length = 1 , columnDefinition = "TinyInt")
	private int gender;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dob;
	
	@NotNull
	@Column(nullable = false , length = 2)
	private int standard;
	
	@NotNull
	@Size(min = 2 , max = 20)
	@Column(nullable = false , length = 20)
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
}
