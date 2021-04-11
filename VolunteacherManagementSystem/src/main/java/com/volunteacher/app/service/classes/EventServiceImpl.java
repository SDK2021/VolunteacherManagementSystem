package com.volunteacher.app.service.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.Event;
import com.volunteacher.app.repository.EventRepository;
import com.volunteacher.app.service.interfaces.EventService;

@Service
public class EventServiceImpl implements EventService{
	
	@Autowired
	EventRepository eventRepository;
	
	@Override
	public ResponseEntity<Object> addEvent(Event event)
	{
		try {
			Event saveEvent = eventRepository.save(event);
			return ResponseEntity.status(HttpStatus.OK).body(saveEvent);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error for creating ");
		}
	}
	
	@Override
	public ResponseEntity<Object> eventList(int page)
	{
		try {
			Pageable pageable = PageRequest.of(page, 5, Sort.by("eventDate"));
			Page<Event> eventList = (Page<Event>) eventRepository.findAll(pageable);
			return ResponseEntity.status(HttpStatus.OK).body(eventList.getContent());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Events");
		}
	}
	
	public ResponseEntity<Object> updateEvent(Event event, int id)
	{
		Event updateEvent = eventRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Event not found for id: " + id));
		
		updateEvent.setTitle(event.getTitle());
		updateEvent.setEventData(event.getEventData());
		updateEvent.setEventDate(event.getEventDate());
		updateEvent.setKids(event.getKids());
		updateEvent.setProject(event.getProject());
		updateEvent.setVillage(event.getVillage());
		updateEvent.setEventEndingTime(event.getEventEndingTime());
		updateEvent.setEventStartingTime(event.getEventStartingTime());
		updateEvent.setActivities(event.getActivities());
		try {
			eventRepository.save(updateEvent);
			return ResponseEntity.status(HttpStatus.OK).body(updateEvent);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in updating Event for id:" +id);
		}
	}
	
	@Override
	public ResponseEntity<Object> deleteEvent(int id)
	{
		eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event not found for id: "+id));
		
		try {
			eventRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Event deleted for id: "+id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in Deleting Event for id:" +id);
		}
		
	}

	@Override
	public ResponseEntity<Object> eventById(int id) 
	{
		try {
			Event event = eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event not found for id: "+id));
			return ResponseEntity.status(HttpStatus.OK).body(event);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Events");
		}
		
	}
}
