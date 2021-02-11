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
public class District {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 3)
	private int districtId;

	@NotNull
	@Column(length = 20, unique = true, nullable = false , columnDefinition = "Char")
	private String districtName;

	@OneToOne
	private State state;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "district")
	private List<Taluka> talukas;

}
