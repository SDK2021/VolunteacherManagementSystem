package com.volunteacher.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Donor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 5)
	private int donorId;

	@NotNull
	@Size(min = 3 , max = 20)
	@Column(columnDefinition = "Char", length = 20, nullable = false)
	private String donorName;

	@NotNull
	@Column(length = 10, nullable = false, unique = true)
	private String donorPhone;

	@NotNull
	@Column(length = 40, nullable = false, unique = true)
	private String donorEmail;

	@NotNull
	@OneToOne
	private UserType usertype;

}
