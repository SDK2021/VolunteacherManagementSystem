package com.volunteacher.app.service.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	public ResponseEntity<Object> announcementList(int page) 
	{
		Pageable pageable = PageRequest.of(page, 6,Sort.by("creationDate").descending());
		try {
			Page<Announcement> announcementList = (Page<Announcement>) announcementRepsitory.findAll(pageable);
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
		try {
			Announcement updateAnnouncement = announcementRepsitory.findById(id).orElseThrow(()-> new ResourceNotFoundException("Announcement not foud for id:"+id));
			
			updateAnnouncement.setData(announcement.getData());
			updateAnnouncement.setCreationDate(announcement.getCreationDate());
			updateAnnouncement.setCreationTime(announcement.getCreationTime());
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
		try {
			announcementRepsitory.findById(id).orElseThrow(()-> new ResourceNotFoundException("Announcement not foud for id:"+id));
			announcementRepsitory.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on deleting Announcement for id:"+ id);
		}
	}

}
