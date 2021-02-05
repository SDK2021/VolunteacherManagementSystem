package com.sdk;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Country {
	
	@Id
	@Column(name="country_id",length=1)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int countryId;
	
	@Column(name="country_name",length=20,nullable=false,unique=true)
	private String countryName;

	@OneToMany(mappedBy="country")
	private List<State> states;
	
	public Country() {
		super();
		
	}

	
	
	

}
