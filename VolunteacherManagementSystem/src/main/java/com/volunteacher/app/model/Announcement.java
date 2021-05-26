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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Announcement {
	
	@Id
	@GeneratedValue(
	    strategy= GenerationType.AUTO,
	    generator="native"
	)
	@GenericGenerator(
	    name = "native",
	    strategy = "native"
	)
	@Column(length = 10, columnDefinition = "BIGINT")
	private Long announcementId;
	
	@Column(nullable = false, columnDefinition = "TEXT")
	private String data;
	
	@ManyToOne
	@CreatedBy
	private User createdBy;
	
	@JsonFormat(shape = Shape.STRING,pattern = "MM-dd-yyyy")
	@NotNull
	@CreatedDate
	@Column(columnDefinition = "DATE")
	private Calendar creationDate;
	
	@NotNull
	@Column(nullable = false, columnDefinition = "TIME")
	@JsonFormat(shape = Shape.STRING, pattern = "HH:mm")
	private Calendar creationTime;

	public Announcement() {
		super();
	}

	public Long getAnnouncementId() {
		return announcementId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	public Calendar getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Calendar creationTime) {
		this.creationTime = creationTime;
	}
}
