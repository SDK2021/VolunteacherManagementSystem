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
	@Column(length = 10	)
	private int postId;
	
	@NotNull
	@ManyToOne
	@CreatedBy
	private User craetedBy;
	
	@NotNull
	@Column(nullable = false)
	private String postPhoto;
	
	@NotNull
	@Column(nullable = false, length = 100)
	private String postTitle;
	
	@NotNull
	@Column(nullable = false, columnDefinition = "Text")
	private String postDescription;
	
	@NotNull
	@CreatedDate
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date creationDate;
	

	public TimelinePost() {
		super();
	}

	public TimelinePost(@NotNull User craetedBy, @NotNull String postPhoto, @NotNull String postTitle,
			@NotNull String postDescription, @NotNull Date creationDate) {
		super();
		this.craetedBy = craetedBy;
		this.postPhoto = postPhoto;
		this.postTitle = postTitle;
		this.postDescription = postDescription;
		this.creationDate = creationDate;
	}

	public User getCraetedBy() {
		return craetedBy;
	}

	public void setCraetedBy(User craetedBy) {
		this.craetedBy = craetedBy;
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "TimelinePost [id=" + postId + ", craetedBy=" + craetedBy + ", postPhoto=" + postPhoto + ", postTitle="
				+ postTitle + ", postDescription=" + postDescription + ", creationDate=" + creationDate + "]";
	}
}
