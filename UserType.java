package com.sdk;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class UserType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 1)
	private int typeId;

	@Column(length = 20, nullable = false, unique = true)
	private String type;

	@OneToMany(mappedBy = "usertype")
	private List<Donor> donors;

	public UserType() {
		super();
	}

	public UserType(int typeId, String type, List<Donor> donors) {
		super();
		this.typeId = typeId;
		this.type = type;
		this.donors = donors;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Donor> getDonors() {
		return donors;
	}

	public void setDonors(List<Donor> donors) {
		this.donors = donors;
	}

	@Override
	public String toString() {
		return "UserType [typeId=" + typeId + ", type=" + type + ", donors=" + donors + "]";
	}

}
