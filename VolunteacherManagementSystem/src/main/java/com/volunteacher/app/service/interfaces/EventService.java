package com.volunteacher.app.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Event;

public interface EventService {
	
	public ResponseEntity<Object> addEvent(Event event);
	
	public ResponseEntity<Object> eventList(int page);
	
	public ResponseEntity<Object> updateEvent(Event event, int id);
	
	public ResponseEntity<Object> eventById(int id);
	
	public ResponseEntity<Object> deleteEvent(int id);
	
}
