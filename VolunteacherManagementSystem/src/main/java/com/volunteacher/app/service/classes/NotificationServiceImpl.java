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
			Notification addNotification = notificationRepository.save(notification);
			return ResponseEntity.status(HttpStatus.CREATED).body(addNotification);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creating Notification");
		}
	}
	
	@Override
	public List<Notification> notificationList()
	{
		List<Notification> notificationList = (List<Notification>) notificationRepository.findAll();
		
		if(notificationList.size() < 1)
			throw new ResourceNotFoundException("Notification list not found");
		
		return  notificationList;
	}
	
	@Override
	public Notification notificationById(Long id)
	{
		Notification notification = notificationRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Notification is not found for id: "+id));
		return notification;
	}
	
	@Override
	public ResponseEntity<Object> updateNotification(Notification notification, Long id) {
		return null;
	}
	 
	@Override
	public ResponseEntity<Object> deleteNotification(Long id)
	{
		notificationRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Notification is not found for id: "+ id));
		notificationRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Notification deleted for id: "+id);
	}
	
}
