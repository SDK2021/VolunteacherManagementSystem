package com.sdk;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Taluka {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 5)
	private int talukaId;

	@Column(length = 20, nullable = false, unique = true)
	private String talukaName;

	@OneToOne
	private District district;

	@OneToMany(mappedBy = "taluka")
	private List<Village> villages;

	public Taluka() {
		super();
	}

	public Taluka(int talukaId, String talukaName, District district, List<Village> villages) {
		super();
		this.talukaId = talukaId;
		this.talukaName = talukaName;
		this.district = district;
		this.villages = villages;
	}

	public int getTalukaId() {
		return talukaId;
	}

	public void setTalukaId(int talukaId) {
		this.talukaId = talukaId;
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

	public List<Village> getVillages() {
		return villages;
	}

	public void setVillages(List<Village> villages) {
		this.villages = villages;
	}

	@Override
	public String toString() {
		return "Taluka [talukaId=" + talukaId + ", talukaName=" + talukaName + ", district=" + district + ", villages="
				+ villages + "]";
	}

}
