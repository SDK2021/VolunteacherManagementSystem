package com.volunteacher.app.model;

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
}
