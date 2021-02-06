package com.sdk;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.persistence.CascadeType;

@Entity
public class Country {

	@Id
	@Column(length = 1, columnDefinition = "TINYINT")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int countryId;

	@NotNull
	@Column(length = 20, nullable = false, unique = true)
	private String countryName;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
	private List<State> states;

	public Country() {
		super();
	}

	public Country(int countryId, String countryName, List<State> states) {
		super();
		this.countryId = countryId;
		this.countryName = countryName;
		this.states = states;
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

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

	@Override
	public String toString() {
		return "Country [countryId=" + countryId + ", countryName=" + countryName + ", states=" + states + "]";
	}

}
