package com.volunteacher.app.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Area {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,
					generator = "native")
	@GenericGenerator(name = "native",
					strategy = "native")
	private int areaId;
	
	@NotNull
	@Column(nullable = false, columnDefinition = "Char(50)", length = 50)
	private String areaName;
	
	@ManyToOne
	private Village village;
	
	@OneToMany(mappedBy = "area")
	private List<Kid> kids;


	public Area() {
		super();
	}

	public Area(int areaId, @NotNull String areaName, Village village) {
		super();
		this.areaId = areaId;
		this.areaName = areaName;
		this.village = village;
	}

	public int getAreaId() {
		return areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Village getVillage() {
		return village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}

	@Override
	public String toString() {
		return "Area [areaId=" + areaId + ", areaName=" + areaName + ", village=" + village + "]";
	}
	
}
