package com.volunteacher.app.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "KID")
public class Kid {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 8)
	private int kidId;
	
	@NotNull
	@Size(min = 3 , max = 20)
	@Column(nullable = false , columnDefinition = "Char")
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
	@Column(nullable = false)
	private String area;
	
	@Max(255)
	private String photo;
	
	@OneToOne
	private School school;
	
	@OneToOne
	private Village village;
	
	@OneToOne
	private KidsGroup grp;
	
	@ManyToMany(mappedBy = "kids")
	private List<Project> projects;
	
	@ManyToMany(mappedBy = "kids")
	private List<Session> sessions;
	
	@ManyToMany(mappedBy = "kids")
	private List<Event> events;
}
