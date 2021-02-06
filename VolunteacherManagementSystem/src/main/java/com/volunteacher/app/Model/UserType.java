package com.volunteachers.database.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

@Entity
public class UserType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="type_id",length=1)
	private int typeid;
		
	@NotNull
	@Column(name="type",length=20,unique=true)
	private String type;


	public UserType() {
		super();		
	}

	public UserType(int typeid, String type) {
		super();
		this.typeid = typeid;
		this.type = type;
	}
	
		public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Usertype [typeid=" + typeid + ", type=" + type + "]";
	}

}
