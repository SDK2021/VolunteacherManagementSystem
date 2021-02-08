package com.volunteacher.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sun.istack.NotNull;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Notification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "notoficationId" , length = 8)
	private int id;
	
	@NotNull
	@Column(length = 15 , nullable = false)
	private String notificationType;
	
	@NotNull
	@Size(min = 3 , max = 30)
	@Column(nullable = false , length = 50)
	private String title;
	
	@NotNull
	@Column(nullable = false , columnDefinition = "Text")
	private String description;
	
	@NotNull
	@CreatedDate
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date createdDate;
	
	@NotNull
	@CreatedBy
	@OneToOne
	private User createdBy;
}
