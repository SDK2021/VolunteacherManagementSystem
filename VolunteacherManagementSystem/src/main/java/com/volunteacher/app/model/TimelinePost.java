package com.volunteacher.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class TimelinePost {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "postId" , length = 10	)
	private int id;
	
	@NotNull
	@ManyToOne
	@CreatedBy
	private User craetedBy;
	
	@NotNull
	@Column(nullable = false)
	private String postPhoto;
	
	@NotNull
	@Column(nullable = false , length = 100)
	private String postTitle;
	
	@NotNull
	@Column(nullable = false , columnDefinition = "Text")
	private String postDescription;
	
	@NotNull
	@CreatedDate
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date creationDate;
}
