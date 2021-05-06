package com.volunteacher.app.service.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.Notification;
import com.volunteacher.app.repository.EventRepository;
import com.volunteacher.app.repository.NotificationRepository;
import com.volunteacher.app.repository.SessionRepository;
import com.volunteacher.app.service.interfaces.EmailService;
import com.volunteacher.app.service.interfaces.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {
	
	@Autowired
	NotificationRepository notificationRepository;
	
	@Autowired
	SessionRepository sessionRepository;
	
	@Autowired 
	EventRepository eventRepository;
	
	@Autowired
	EmailService emailService;
	
	@Override
	public ResponseEntity<Object> addNotification(Notification notification)
	{
		try {
			notification.setUserType(notification.getUserType().toUpperCase());
			if(notification.getSession() != null)
			{
				notification.getSession().setNotified(true);
				sessionRepository.save(notification.getSession());
			}
			if(notification.getEvent() !=null)
			{
				notification.getSession().setNotified(true);
				eventRepository.save(notification.getEvent());
			}
			Notification saveNotification = notificationRepository.save(notification);
			if(notification.getSession() != null)
			{
				emailService.notificationSessionMail(notification.getUserType().toUpperCase());
			}
			if(notification.getEvent() !=null)
			{
				emailService.notificationEventMail(notification.getUserType().toUpperCase());
			}
			
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
			List<Notification> notificationList = (List<Notification>) notificationRepository.notificationByMonthAndYear(month, year, userType);
			
//			if(notificationList.size() < 1)
//				throw new ResourceNotFoundException("Notification list not found");
			
			return ResponseEntity.status(HttpStatus.OK).body(notificationList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Notifications");
		}
	}
	
	@Override
	public ResponseEntity<Object> notificationAdminFilter()
	{
		try {
			List<Notification> notificationList = (List<Notification>) notificationRepository.notificationAdminFilter();
			
//			if(notificationList.size() < 1)
//				throw new ResourceNotFoundException("Notification list not found");
			
			return ResponseEntity.status(HttpStatus.OK).body(notificationList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Notifications");
		}
	}
	
	@Override
	public ResponseEntity<Object> notificationListByUser(String userType)
	{
		try {
			List<Notification> notificationList = (List<Notification>) notificationRepository.notificationByUser(userType);
			
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
	 
	@Override
	public ResponseEntity<Object> deleteNotification(Long id)
	{
		notificationRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Notification is not found for id: "+ id));
		
		try {
			notificationRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in Deleting Notification for id:" +id);
		}
	}
	
}
