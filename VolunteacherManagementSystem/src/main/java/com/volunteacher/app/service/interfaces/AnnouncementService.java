package com.volunteacher.app.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Announcement;

public interface AnnouncementService {
	
	public ResponseEntity<Object> announcementList(int page);
	
	public ResponseEntity<Object> addAnnouncement(Announcement announcement);
	
	public ResponseEntity<Object> updateAnnouncement(Announcement announcement, Long id);
	
	public ResponseEntity<Object> deleteAnnouncement(Long id);

}
