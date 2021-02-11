package com.volunteacher.app.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.persistence.CascadeType;

@Entity
public class State {

	@Id
	@Column(length = 2, columnDefinition = "TINYINT")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int stateId;

	@NotNull
	@Column(length = 20, unique = true, nullable = false , columnDefinition = "Char")
	private String stateName;

	@NotNull
	@OneToOne
	private Country country;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "state")
	private List<District> districts;
}