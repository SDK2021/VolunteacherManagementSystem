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
	
	@OneToMany(mappedBy = "village")
	private List<Area> areas;
	
	@OneToMany(mappedBy = "village")
	private List<Kid> kids;
	

	public Village() {
		super();
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
