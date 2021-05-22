package com.volunteacher.app.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(EventKidActivity.class)
public class EventKidActivity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	private Kid kid;
	
	@Id
	@ManyToOne
	private Event event;
	
	
	@ManyToOne
	private Activity activity;
}
