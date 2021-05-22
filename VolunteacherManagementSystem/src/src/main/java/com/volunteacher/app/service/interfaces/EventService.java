package com.volunteacher.app.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Activity;
import com.volunteacher.app.model.Event;
import com.volunteacher.app.model.Kid;

public interface EventService {
	
	public ResponseEntity<Object> addEvent(Event event,String[] ids);
	
	public ResponseEntity<Object> eventList(int page);
	
	public ResponseEntity<Object> getAllEvents(int page);
	
	public ResponseEntity<Object> updateEvent(Event event, int id,String[] activityIds);
	
	public ResponseEntity<Object> totalKidsByEvent(int eventId);
	
	public ResponseEntity<Object> eventById(int id);
	
	public ResponseEntity<Object> deleteEvent(int id);
	
	public ResponseEntity<Object> addKidsParticipants(String [] ids,String eventId);
	
	public List<Kid> createKidsParticipantsList(String[] ids);
	
	public List<Activity> createActivityList(String[] ids);
	
	public ResponseEntity<Object> getTotalEvents();
	
}
