package com.volunteacher.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class KidsGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 2)
	private int groupId;

	@NotNull
	@Column(length = 20, nullable = false , columnDefinition = "Char")
	private String groupName;

	@NotNull
	@Column(length = 20, nullable = false)
	private String criteria;

}
