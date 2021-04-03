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

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Taluka {

	@Id
	@GeneratedValue(
	    strategy= GenerationType.AUTO,
	    generator="native"
	)
	@GenericGenerator(
	    name = "native",
	    strategy = "native"
	)
	@Column(length = 5)
	private int talukaId;

	@NotNull
	@Column(length = 50, nullable = false, columnDefinition = "Char(50)")
	private String talukaName;

	@NotNull
	@OneToOne
	private District district;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "taluka")
	private List<Village> villages;

	
	public Taluka() {
		super();
	}

	public Taluka(String talukaName, District district) {
		super();
		this.talukaName = talukaName;
		this.district = district;
	}
	
	public int getTalukaId() {
		return talukaId;
	}

	public String getTalukaName() {
		return talukaName;
	}

	public void setTalukaName(String talukaName) {
		this.talukaName = talukaName;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	@Override
	public String toString() {
		return "Taluka [talukaId=" + talukaId + ", talukaName=" + talukaName + ", district=" + district + "]";
	}
}
