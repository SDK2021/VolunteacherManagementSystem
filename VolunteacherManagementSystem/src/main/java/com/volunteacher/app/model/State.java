package com.volunteacher.app.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class State {

	@Id
	@GeneratedValue(
	    strategy= GenerationType.AUTO,
	    generator="native"
	)
	@GenericGenerator(
	    name = "native",
	    strategy = "native"
	)
	@Column(length = 2, columnDefinition = "TINYINT")
	private int stateId;

	@NotNull
	@Column(length = 50, unique = true, nullable = false , columnDefinition = "Char(50)")
	private String stateName;

	@NotNull
	@OneToOne
	private Country country;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "state")
	private List<District> districts;

	
	public State() {
		super();
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
}