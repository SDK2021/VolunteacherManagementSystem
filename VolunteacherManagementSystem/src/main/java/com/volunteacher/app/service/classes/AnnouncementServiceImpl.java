package com.volunteacher.app.service.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.Announcement;
import com.volunteacher.app.repository.AnnouncementRepository;
import com.volunteacher.app.service.interfaces.AnnouncementService;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
	
	@Autowired
	AnnouncementRepository announcementRepsitory;

	@Override
	public ResponseEntity<Object> announcementList() 
	{
		try {
			List<Announcement> announcementList = (List<Announcement>) announcementRepsitory.findAll();
			return ResponseEntity.status(HttpStatus.OK).body(announcementList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch announcement list");
		}
	}

	@Override
	public ResponseEntity<Object> addAnnouncement(Announcement announcement) 
	{
		try {
			Announcement saveannouncement = announcementRepsitory.save(announcement);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveannouncement);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error On creating announcement");
		}
	}

	@Override
	public ResponseEntity<Object> updateAnnouncement(Announcement announcement, Long id) 
	{
		Announcement updateAnnouncement = announcementRepsitory.findById(id).orElseThrow(()-> new ResourceNotFoundException("Announcement not foud for id:"+id));
		
		updateAnnouncement.setData(announcement.getData());
		updateAnnouncement.setCreationDate(announcement.getCreationDate());
		updateAnnouncement.setCreationTime(announcement.getCreationTime());
		try {
			announcementRepsitory.save(updateAnnouncement);
			return ResponseEntity.status(HttpStatus.OK).body(updateAnnouncement);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on updating announcement for id:" +id);
		}
		
	}
	
	@Override
	public ResponseEntity<Object> deleteAnnouncement(Long id)
	{
		announcementRepsitory.findById(id).orElseThrow(()-> new ResourceNotFoundException("Announcement not foud for id:"+id));
		
		try {
			announcementRepsitory.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Announcement Delete for Id:"+id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on deleting Announcement for id:"+ id);
		}
	}

}