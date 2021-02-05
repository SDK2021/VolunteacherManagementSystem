package com.sdk;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.sun.istack.NotNull;


@Entity
public class Donor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="donor_id",length=5)
	private int donorId;
	
	@Column(name="donor_name",length=20,nullable=false)
	private String donorName;
	
	@Column(name="donor_phone",length=10,nullable=false,unique=true)
	private int donorPhone;
	
	@Column(name="donor_email",length=40,nullable=false,unique=true)
	private String donorEmail;
	
	@NotNull
	@OneToOne
	private UserType usertype;
	

	

}
