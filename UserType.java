package com.sdk;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class UserType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 1,columnDefinition = "TINYINT")
	private int typeId;

	@NotNull
	@Column(length = 20, nullable = false, unique = true)
	private String type;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "UserType [typeId=" + typeId + ", type=" + type + "]";
	}

}
