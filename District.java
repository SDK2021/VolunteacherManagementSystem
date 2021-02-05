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
public class District {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="district_id",length=3)
	private int districtId;
	
	@Column(name="district_name",length=20,unique=true,nullable=false)
	private String districtName;
	
	@OneToOne
	private State state;
   
	@OneToMany(mappedBy="district")
	private List<Taluka> talukas;

	public District() {
		super();
	}


	

}
