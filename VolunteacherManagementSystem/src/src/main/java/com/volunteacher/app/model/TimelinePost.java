package com.volunteacher.app.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class TimelinePost {
	
	@Id
	@GeneratedValue(
	    strategy= GenerationType.AUTO,
	    generator="native"
	)
	@GenericGenerator(
	    name = "native",
	    strategy = "native"
	)
	@Column(length = 10)
	private Long postId;
	
	@NotNull
	@Column(nullable = false)
	private String postPhoto;
	
	@NotNull
	@Column(nullable = false, columnDefinition = "Text")
	private String postDescription;
	
	@NotNull
	@CreatedDate
	@JsonFormat(shape = Shape.STRING,pattern = "MM-dd-yyyy")
	@Column(nullable = false)
	private Calendar creationDate;
	
	@NotNull
	@ManyToOne
	@CreatedBy
	private User createdBy;
	
	public TimelinePost() {
		super();
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public String getPostPhoto() {
		return postPhoto;
	}

	public void setPostPhoto(String postPhoto) {
		this.postPhoto = postPhoto;
	}

	public String getPostDescription() {
		return postDescription;
	}

	public void setPostDescription(String postDescription) {
		this.postDescription = postDescription;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}	
}
