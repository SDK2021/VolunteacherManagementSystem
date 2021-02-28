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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	@JsonBackReference
	private State state;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "district")
	@JsonManagedReference
	private List<Taluka> talukas;
	

	public District() {
		super();
	}

	public District(@NotNull String districtName, State state, List<Taluka> talukas) {
		super();
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
