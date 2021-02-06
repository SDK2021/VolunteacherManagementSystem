package com.volunteachers.database.model;

import javax.persistence.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class State {
	@Id
	@Column(length = 2, columnDefinition = "TINYINT")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int stateId;

	@NotNull
	@Column(length = 20, unique = true, nullable = false)
	private String stateName;

	@OneToOne
	private Country country;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "state")
	private List<District> districts;

	public State() {
		super();
	}

	public State(int stateId, String stateName, Country country, List<District> districts) {
		super();
		this.stateId = stateId;
		this.stateName = stateName;
		this.country = country;
		this.districts = districts;
	}

	public int getStateId() {
		return stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<District> getDistricts() {
		return districts;
	}

	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}

	@Override
	public String toString() {
		return "State [stateId=" + stateId + ", stateName=" + stateName + ", country=" + country + ", districts="
				+ districts + "]";
	}
}
