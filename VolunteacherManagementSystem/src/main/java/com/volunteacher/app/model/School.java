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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "SCHOOL")
public class School {
	
	@Id
	@Column(name = "school_id" , length = 3)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
	@Size(min = 2 , max = 30)
	@Column(name = "name" , nullable = false)
	private String name;
	
	@Column(name = "pincode" , length = 6)
	private int pincode;
	
	@Column(name = "total_labs" , length = 5)
	private int totalLabs;
	
	@NotNull
	@Column(name = "phone_number" , nullable = false , length = 10 , unique = true)
	private String phoneNumber;
	
	@Temporal(TemporalType.DATE)
	private Date startingDate;
	
	@NotNull
	@Size(min = 2 , max = 20)
	@Column(name = "Stream" , nullable = false)
	private String stream;
	
	@NotNull
	@Column(	nullable = false , length = 4)
	private int totalStudent;
	
	@NotNull
	@Column(name = "status" , nullable = false , length = 1 , columnDefinition = "TinyInt") 
	private int status;
	
	@ManyToMany(mappedBy = "schools")
	private List<Requirement> requirements;
	
	@OneToOne
	Village village;
}
