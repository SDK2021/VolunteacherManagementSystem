package com.volunteacher.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.volunteacher.app.model.Announcement;
import com.volunteacher.app.service.interfaces.AnnouncementService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/vms")
public class AnnouncementController {
	
	@Autowired
	AnnouncementService announcementService;
	
	@GetMapping("/announcements")
	public ResponseEntity<Object> getAnnouncementList()
	{
		return announcementService.announcementList();
	}
	
	@PostMapping("/announcements")
	public ResponseEntity<Object> addAnnouncement(@RequestBody Announcement announcement)
	{
		return announcementService.addAnnouncement(announcement);
	}
	
	@PutMapping("/announcements/{id}")
	public ResponseEntity<Object> updateAnnouncement(@RequestBody Announcement announcement,@PathVariable Long id)
	{
		return announcementService.updateAnnouncement(announcement, id);
	}
	
	@DeleteMapping("/announcements/{id}")
	public ResponseEntity<Object> deleteAnnouncement(@PathVariable Long id)
	{
		return announcementService.deleteAnnouncement(id);
	}
}
