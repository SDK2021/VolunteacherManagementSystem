package com.volunteacher.app.service.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
			Event addEvent = eventRepository.save(event);
			return ResponseEntity.status(HttpStatus.OK).body(addEvent);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error for creating ");
		}
	}
	
	@Override
	public List<Event> eventList()
	{
		List<Event> eventList = (List<Event>) eventRepository.findAll();
		if(eventList.size() < 1)
			throw new ResourceNotFoundException("Event list not found");
		
		return eventList;
	}
	
	public ResponseEntity<Object> updateEvent(Event event, int id)
	{
		Event updateEvent = eventRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Event not found for id: " + id));
		
		updateEvent.setTitle(event.getTitle());
		updateEvent.setEventData(event.getEventData());
		updateEvent.setEventDate(event.getEventDate());
		updateEvent.setEventTime(event.getEventTime());
		updateEvent.setKids(event.getKids());
		updateEvent.setProject(event.getProject());
		updateEvent.setVillage(event.getVillage());
		
		eventRepository.save(updateEvent);
		
		return ResponseEntity.status(HttpStatus.OK).body(updateEvent);
	}
	
	@Override
	public ResponseEntity<Object> deleteEvent(int id)
	{
		eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event not found for id: "+id));
		eventRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Event deleted for id: "+id);
	}
}
