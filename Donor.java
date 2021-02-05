package com.sdk;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;




@Entity
public class Donor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length=5)
	private int donorId;
	
	@Column(length=20,nullable=false)
	private String donorName;
	
	@Column(length=10,nullable=false,unique=true)
	private int donorPhone;
	
	@Column(length=40,nullable=false,unique=true)
	private String donorEmail;
	
	@OneToOne
	private UserType usertype;
	

	

}
