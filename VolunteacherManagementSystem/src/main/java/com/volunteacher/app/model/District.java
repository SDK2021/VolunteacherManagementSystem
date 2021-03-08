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

@Entity
public class District {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(length = 3)
	private int districtId;

	@NotNull
	@Column(length = 20, unique = true, nullable = false , columnDefinition = "Char")
	private String districtName;

	@OneToOne
	private State state;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "district")
	private List<Taluka> talukas;
	

	public District() {
		super();
	}

	public District(String districtName, State state) {
		super();
		this.districtName = districtName;
		this.state = state;
	}
	
	public int getDistrictId() {
		return districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "District [districtId=" + districtId + ", districtName=" + districtName + ", state=" + state
				 + "]";
	}
}
