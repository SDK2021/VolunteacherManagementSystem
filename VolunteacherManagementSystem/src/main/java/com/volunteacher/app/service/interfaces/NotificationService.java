package com.volunteacher.app.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Notification;

public interface NotificationService {

	public ResponseEntity<Object> addNotification(Notification notification);
	
	public ResponseEntity<Object> notificationList();
	
//	public ResponseEntity<Object> updateNotification(Notification notification, Long id);
	
	public ResponseEntity<Object> notificationById(Long id);
	
	public ResponseEntity<Object> deleteNotification(Long id);
}
