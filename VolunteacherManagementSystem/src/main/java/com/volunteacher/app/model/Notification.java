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
	@Column(length = 8)
	private int notificationId;
	
	@NotNull
	@Column(length = 15, nullable = false)
	private String notificationType;
	
	@NotNull
	@Size(min = 3, max = 30)
	@Column(nullable = false, length = 50)
	private String title;
	
	@NotNull
	@Column(nullable = false, columnDefinition = "Text")
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
	

	public Notification() {
		super();
	}
	
	public Notification(String notificationType, @Size(min = 3, max = 30) String title, String description,
			Date createdDate, User createdBy) {
		super();
		this.notificationType = notificationType;
		this.title = title;
		this.description = description;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public String toString() {
		return "Notification [id=" + notificationId + ", notificationType=" + notificationType + ", title=" + title
				+ ", description=" + description + ", createdDate=" + createdDate + ", createdBy=" + createdBy + "]";
	}
}
