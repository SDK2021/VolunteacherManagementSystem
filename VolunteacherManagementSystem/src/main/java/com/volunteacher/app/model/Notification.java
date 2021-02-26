package com.volunteacher.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
	@CreatedBy
	@OneToOne
	private User createdBy;
	
	@NotNull
	@Column(nullable = false , length = 1)
	private int userType;
	
	//add
	@OneToOne
	private Session session;
	
	@OneToOne
	private Event event;
	

	public Notification() {
		super();
	}
	
	public Notification(String notificationType, @Size(min = 3, max = 30) String title, String description,
			User createdBy) {
		super();
		this.notificationType = notificationType;
		this.createdBy = createdBy;
	}

	public int getNotificationId() {
		return notificationId;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return "Notification [notificationId=" + notificationId + ", notificationType=" + notificationType
				+ ", createdBy=" + createdBy + ", userType=" + userType + ", session=" + session + ", event=" + event
				+ "]";
	}
}
