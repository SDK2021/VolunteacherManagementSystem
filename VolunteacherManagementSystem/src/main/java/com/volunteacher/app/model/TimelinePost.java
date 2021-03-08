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

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class TimelinePost {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(length = 10)
	private Long postId;
	
	@NotNull
	@ManyToOne
	@CreatedBy
	private User createdBy;
	
	@NotNull(message = "post photo not be null")
	@Column(nullable = false)
	private String postPhoto;
	
	@NotNull(message = "post title not be null")
	@Column(nullable = false, length = 100)
	private String postTitle;
	
	@NotNull
	@Column(nullable = false, columnDefinition = "Text")
	private String postDescription;
	
	@NotNull
	@CreatedDate
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(nullable = false)
	private Calendar creationDate;
	

	public TimelinePost() {
		super();
	}

	public TimelinePost(User createdBy, String postPhoto, String postTitle, String postDescription, Calendar creationDate) {
		super();
		this.createdBy = createdBy;
		this.postPhoto = postPhoto;
		this.postTitle = postTitle;
		this.postDescription = postDescription;
		this.creationDate = creationDate;
	}

	public Long getPostId() {
		return postId;
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

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
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

	@Override
	public String toString() {
		return "TimelinePost [id=" + postId + ", craetedBy=" + createdBy + ", postPhoto=" + postPhoto + ", postTitle="
				+ postTitle + ", postDescription=" + postDescription + ", creationDate=" + creationDate + "]";
	}
}
