package com.volunteacher.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Village {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 6)
	private int villageId;

	@NotNull
	@Column(length = 20, nullable = false)
	private String villageName;

	@OneToOne
	private Taluka taluka;

}
