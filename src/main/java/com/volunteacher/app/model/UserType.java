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
	@Column(name="type_id" , length=1 , columnDefinition = "TinyInt")
	private int typeid;
		
	@NotNull
	@Column(name="type",length=20,unique=true)
	private String type;

}
