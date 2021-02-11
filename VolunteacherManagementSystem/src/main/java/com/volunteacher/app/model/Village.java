package com.volunteacher.app.model;

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
	@Column(length = 20, nullable = false , columnDefinition = "Char")
	private String villageName;

	@NotNull
	@OneToOne
	private Taluka taluka;
	

	public Village() {
		super();
	}

	public Village(@NotNull String villageName, @NotNull Taluka taluka) {
		super();
		this.villageName = villageName;
		this.taluka = taluka;
	}
	
	public int getVillageId() {
		return villageId;
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
