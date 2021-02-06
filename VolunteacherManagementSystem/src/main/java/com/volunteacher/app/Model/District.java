package com.volunteachers.database.model;

import javax.persistence.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class District {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 3)
	private int districtId;

	@NotNull
	@Column(length = 20, unique = true, nullable = false)
	private String districtName;

	@OneToOne
	private State state;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "district")
	private List<Taluka> talukas;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "district")
	private List<Volunteacher> volunteachers;

	public District() {
		super();
	}

	public District(int districtId, String districtName, State state, List<Taluka> talukas) {
		super();
		this.districtId = districtId;
		this.districtName = districtName;
		this.state = state;
		this.talukas = talukas;
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

	public List<Taluka> getTalukas() {
		return talukas;
	}

	public void setTalukas(List<Taluka> talukas) {
		this.talukas = talukas;
	}

	@Override
	public String toString() {
		return "District [districtId=" + districtId + ", districtName=" + districtName + ", state=" + state
				+ ", talukas=" + talukas + "]";
	}
}
