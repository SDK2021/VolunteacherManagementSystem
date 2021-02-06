package com.volunteachers.database.model;

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
public class Village {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 6)
	private int villageId;

	@NotNull
	@Column(length = 20, nullable = false)
	private String villageName;

	@OneToOne
	private Taluka taluka;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "village")
	private List<Event> events;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "village")
	private List<Session> sessions;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "village")
	private List<Volunteacher> volunteachers;


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
