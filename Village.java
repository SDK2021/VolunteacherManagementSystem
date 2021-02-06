package com.sdk;

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
	@Column(length = 20, nullable = false, unique = true)
	private String villageName;

	@OneToOne
	private Taluka taluka;

	public Village() {
		super();

	}

	public Village(int villageId, String villageName, Taluka taluka) {
		super();
		this.villageId = villageId;
		this.villageName = villageName;
		this.taluka = taluka;
	}

	public int getVillageId() {
		return villageId;
	}

	public void setVillageId(int villageId) {
		this.villageId = villageId;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public Taluka getTaluka() {
		return taluka;
	}

	public void setTaluka(Taluka taluka) {
		this.taluka = taluka;
	}

	@Override
	public String toString() {
		return "Village [villageId=" + villageId + ", villageName=" + villageName + ", taluka=" + taluka + "]";
	}

}
