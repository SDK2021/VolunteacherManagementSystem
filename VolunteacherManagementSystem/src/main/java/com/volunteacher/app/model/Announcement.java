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
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@NotNull
	@CreatedDate
	private Calendar creationDate;
	
	@NotNull
	@Column(nullable = false, columnDefinition = "TIME")
	@JsonFormat(timezone = "IST", pattern = "HH-mm-ss")
	@CreationTimestamp
	private Calendar creationTime;

	public Announcement() {
		super();
	}

	public Announcement(String data, User createdBy, Calendar creationDate,
			Calendar creationTime) {
		super();
		this.data = data;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.creationTime = creationTime;
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

	public User getUser() {
		return createdBy;
	}

	public void setUser(User user) {
		this.createdBy = user;
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

	@Override
	public String toString() {
		return "Announcement [announcementId=" + announcementId + ", data=" + data + ", user=" + createdBy
				+ ", creationDate=" + creationDate + ", creationTime=" + creationTime + "]";
	}
}
