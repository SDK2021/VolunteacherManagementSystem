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
	
//	@NotNull
//	@Column(nullable = false, length = 100)
//	private String postTitle;
	
	@NotNull
	@Column(nullable = false, columnDefinition = "Text")
	private String postDescription;
	
	@NotNull
	@CreatedDate
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(nullable = false)
	private Calendar creationDate;
	
//	@NotNull
//	@Column(nullable = false, columnDefinition = "TIME")
//	@JsonFormat(pattern = "HH-mm-ss")
//	@CreationTimestamp
//	private Calendar creationTime;
	
	private int likes;
	
	@NotNull
	@ManyToOne
	@CreatedBy
	private User createdBy;
	
	public TimelinePost() {
		super();
	}

	public TimelinePost(Long postId, User createdBy,String postPhoto,
			String postTitle, String postDescription, Calendar creationDate, 
			Calendar creationTime, int likes) {
		super();
		this.postId = postId;
		this.createdBy = createdBy;
		this.postPhoto = postPhoto;
//		this.postTitle = postTitle;
		this.postDescription = postDescription;
		this.creationDate = creationDate;
//		this.creationTime = creationTime;
		this.likes = likes;
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

//	public String getPostTitle() {
//		return postTitle;
//	}
//
//	public void setPostTitle(String postTitle) {
//		this.postTitle = postTitle;
//	}

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

//	public Calendar getCreationTime() {
//		return creationTime;
//	}
//
//	public void setCreationTime(Calendar creationTime) {
//		this.creationTime = creationTime;
//	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	@Override
	public String toString() {
		return "TimelinePost [postId=" + postId + ", postPhoto=" + postPhoto + ", postDescription=" + postDescription
				+ ", creationDate=" + creationDate + ", likes=" + likes + ", createdBy=" + createdBy + "]";
	}
	
}
