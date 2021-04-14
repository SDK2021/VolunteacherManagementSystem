package com.volunteacher.app.service.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.Notification;
import com.volunteacher.app.repository.NotificationRepository;
import com.volunteacher.app.service.interfaces.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {
	
	@Autowired
	NotificationRepository notificationRepository;
	
	@Override
	public ResponseEntity<Object> addNotification(Notification notification)
	{
		try {
			notification.setUserType(notification.getUserType().toUpperCase());
			Notification saveNotification = notificationRepository.save(notification);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveNotification);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creating Notification");
		}
	}
	
	@Override
	public ResponseEntity<Object> notificationList(int month, int year, String userType)
	{
		try {
			List<Notification> notificationList = (List<Notification>) notificationRepository.notificationByMonthAndYear(month, year,userType);
			
//			if(notificationList.size() < 1)
//				throw new ResourceNotFoundException("Notification list not found");
			
			return ResponseEntity.status(HttpStatus.OK).body(notificationList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Notifications");
		}
	}
	
	@Override
	public ResponseEntity<Object> notificationById(Long id)
	{
		try {
			Notification notification = notificationRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Notification is not found for id: "+id));
			return ResponseEntity.status(HttpStatus.OK).body(notification);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Notofications");
		}
	}
	
//	@Override
//	public ResponseEntity<Object> updateNotification(Notification notification, Long id) {
//		return null;
//	}
	 
	@Override
	public ResponseEntity<Object> deleteNotification(Long id)
	{
		notificationRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Notification is not found for id: "+ id));
		
		try {
			notificationRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Notification deleted for id: "+id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in Deleting Notification for id:" +id);
		}
	}
	
}
