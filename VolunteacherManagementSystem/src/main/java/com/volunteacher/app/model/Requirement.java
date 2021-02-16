package com.volunteacher.app.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;


@Entity
public class Requirement {
	
	@Id
	@Column(length = 3)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int requirementId;
	
	@NotNull
	@Column(name = "requirement", columnDefinition = "Text")
	private String requirement;
	
	@ManyToMany
	private List<School> schools;

	
	public Requirement() {
		super();
	}

	public Requirement( String requirement, List<School> schools) {
		super();
		this.requirement = requirement;
		this.schools = schools;
	}
	
	public int getRequirementId() {
		return requirementId;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public List<School> getSchools() {
		return schools;
	}

	public void setSchools(List<School> schools) {
		this.schools = schools;
	}

	@Override
	public String toString() {
		return "Requirement [id=" + requirementId + ", requirement=" + requirement + ", schools=" + schools + "]";
	}
}
