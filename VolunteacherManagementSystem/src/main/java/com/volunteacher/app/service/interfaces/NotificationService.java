package com.volunteacher.app.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Notification;

public interface NotificationService {

	public ResponseEntity<Object> addNotification(Notification notification);
	
	public ResponseEntity<Object> notificationList(int page,int month, int year,String userType);
	
//	public ResponseEntity<Object> updateNotification(Notification notification, Long id);
	
	public ResponseEntity<Object> notificationById(Long id);
	
	public ResponseEntity<Object> deleteNotification(Long id);
	
	public ResponseEntity<Object> notificationListByUser(int page,String userType);
	
	public ResponseEntity<Object> notificationAdminFilter(int page);
}
