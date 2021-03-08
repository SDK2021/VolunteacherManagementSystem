package com.volunteacher.app.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Event;

public interface EventService {
	
	public ResponseEntity<Object> addEvent(Event event);
	
	public List<Event> eventList();
	
	public ResponseEntity<Object> updateEvent(Event event, int id);
	
	public ResponseEntity<Object> deleteEvent(int id);
	
}
