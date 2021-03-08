package com.volunteacher.app.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Notification;

public interface NotificationService {

	public ResponseEntity<Object> addNotification(Notification notification);
	
	public List<Notification> notificationList();
	
	public ResponseEntity<Object> updateNotification(Notification notification, Long id);
	
	public Notification notificationById(Long id);
	
	public ResponseEntity<Object> deleteNotification(Long id);
}
