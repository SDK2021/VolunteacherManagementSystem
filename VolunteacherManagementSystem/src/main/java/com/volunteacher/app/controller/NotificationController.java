package com.volunteacher.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.volunteacher.app.model.Notification;
import com.volunteacher.app.service.interfaces.NotificationService;

@RestController
@RequestMapping(path = "/vms")
public class NotificationController {
	
	@Autowired
	NotificationService notificationService;
	
	@PostMapping("/notifications")
	public ResponseEntity<Object> addNotification(@RequestBody Notification notification)
	{
		return notificationService.addNotification(notification);
	}
	
	@GetMapping("/notifications")
	public ResponseEntity<Object> getNotificationList(@RequestParam("page")int page,@RequestParam("month") int month, @RequestParam("year") int year,@RequestParam("usertype") String userType)
	{
		return notificationService.notificationList(page,month,year,userType);
	}
	
	@GetMapping("/users-notifications")
	public ResponseEntity<Object> getNotificationListByUser(@RequestParam("page")int page,@RequestParam("usertype") String userType)
	{
		return notificationService.notificationListByUser(page,userType);
	}
	
	@GetMapping("/admin-notifications")
	public ResponseEntity<Object> getNotificationAdminFilter(@RequestParam("page") int page)
	{
		return notificationService.notificationAdminFilter(page);
	}

	@GetMapping("/notifications/{id}")
	public ResponseEntity<Object> getNotification(@PathVariable Long id)
	{
		return notificationService.notificationById(id);
	}
	
	@DeleteMapping("/notifications/{id}")
	public ResponseEntity<Object> deleteNotification(@PathVariable Long id)
	{
		return notificationService.deleteNotification(id);
	}
	
}
