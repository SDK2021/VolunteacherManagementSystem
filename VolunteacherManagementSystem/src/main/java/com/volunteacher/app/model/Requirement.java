package com.volunteacher.app.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "REQUIREMENT")
public class Requirement {
	
	@Id
	@Column(name = "requirement_id" , length = 3)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
	@Column(name = "requirement" , columnDefinition = "Text")
	private String requirement;
	
	@ManyToMany
	private List<School> schools;
}
