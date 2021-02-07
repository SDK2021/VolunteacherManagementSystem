package com.volunteacher.app.model;

import java.util.List;
import javax.validation.constraints.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;

@Entity
public class Taluka {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 5)
	private int talukaId;

	@NotNull
	@Column(length = 20, nullable = false, unique = true)
	private String talukaName;

	@OneToOne
	private District district;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "taluka")
	private List<Village> villages;

}
