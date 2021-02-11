package com.volunteacher.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class UserType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length=1 , columnDefinition = "TinyInt")
	private int typeId;
		
	@NotNull
	@Column(length = 20, unique = true)
	private String type;

	
	public UserType() {
		super();
	}

	public UserType(String type) {
		super();
		this.type = type;
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
