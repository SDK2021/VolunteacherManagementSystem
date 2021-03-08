package com.volunteacher.app.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Country {

	@Id
	@Column(length = 1, columnDefinition = "TINYINT")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int countryId;

	@NotNull
	@Column(length = 20, nullable = false, unique = true , columnDefinition = "Char")
	private String countryName;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
	private List<State> states;

	
	public Country() {
		super();
	}

	public Country(String countryName) {
		super();
		this.countryName = countryName;
	}

	public int getCountryId() {
		return countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Override
	public String toString() {
		return "Country [countryId=" + countryId + ", countryName=" + countryName +"]";
	}
}
