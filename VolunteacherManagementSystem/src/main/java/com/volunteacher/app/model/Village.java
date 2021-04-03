package com.volunteacher.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Village {

	@Id
	@GeneratedValue(
	    strategy= GenerationType.AUTO,
	    generator="native"
	)
	@GenericGenerator(
	    name = "native",
	    strategy = "native"
	)
	@Column(length = 6)
	private int villageId;

	@NotNull
	@Column(length = 50, nullable = false , columnDefinition = "Char(50)")
	private String villageName;

	@NotNull
	@OneToOne
	private Taluka taluka;
	

	public Village() {
		super();
	}

	public Village(String villageName, Taluka taluka) {
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
