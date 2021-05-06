package com.volunteacher.app.service.classes;

import java.util.ArrayList;
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
import com.volunteacher.app.model.Activity;
import com.volunteacher.app.model.Event;
import com.volunteacher.app.model.Kid;
import com.volunteacher.app.repository.ActivityRepository;
import com.volunteacher.app.repository.EventRepository;
import com.volunteacher.app.repository.KidRepository;
import com.volunteacher.app.service.interfaces.EmailService;
import com.volunteacher.app.service.interfaces.EventService;

@Service
public class EventServiceImpl implements EventService{
	
	@Autowired
	EventRepository eventRepository;
	
	List<Kid> kids;
	
	List<Activity> activities;
	
	@Autowired
	ActivityRepository activityRepository;
	
	@Autowired
	KidRepository kidRepository;
	
	@Autowired
	EmailService emailService;
	
	@Override
	public ResponseEntity<Object> addEvent(Event event,String[] activityIds)
	{
		try {
			event.setActivities(this.createActivityList(activityIds));
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
			Pageable pageable = PageRequest.of(page, 5, Sort.by("event_date"));
			Page<Event> eventList = (Page<Event>) eventRepository.eventByMonthAndYear(pageable);
			return ResponseEntity.status(HttpStatus.OK).body(eventList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Events");
		}
	}
	
	@Override
	public ResponseEntity<Object> getAllEvents(int page)
	{
		try {
			Pageable pageable = PageRequest.of(page, 10, Sort.by("eventDate").descending());
			Page<Event> eventList = (Page<Event>) eventRepository.findAll(pageable);
			return ResponseEntity.status(HttpStatus.OK).body(eventList);
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
			return ResponseEntity.status(HttpStatus.OK).build();
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

	@Override
	public ResponseEntity<Object> addKidsParticipants(String[] ids, String eventId) {
		Event event = eventRepository.findById(Integer.parseInt(eventId)).orElseThrow(()->new ResourceNotFoundException("Event id not found on add kids participants"));
		
		try {
			event.setKids(this.createKidsParticipantsList(ids));
			eventRepository.save(event);
			return ResponseEntity.status(HttpStatus.OK).body(event);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on adding participants");
		}
	}
	
	@Override
	public List<Kid> createKidsParticipantsList(String[] ids)
	{
		this.kids = new ArrayList<Kid>();
		for(int i =0; i<ids.length;i++)
		{
			Kid kid = this.kidRepository.findById(Long.parseLong(ids[i])).orElseThrow(()->new ResourceNotFoundException("Kid Id not found on add participants"));
			this.kids.add(kid);
		}
		return this.kids;
	}
	
	@Override
	public List<Activity> createActivityList(String[] ids) 
	{
		this.activities = new ArrayList<Activity>();
		for(int i =0; i<ids.length;i++)
		{
			Activity activity = this.activityRepository.findById(Integer.parseInt(ids[i])).orElseThrow(()->new ResourceNotFoundException("Activity Id not found on add Event"));
			this.activities.add(activity);
		}
		return this.activities;
	}
	
	@Override
	public ResponseEntity<Object> getTotalEvents() 
	{
		try 
		{
			return ResponseEntity.status(HttpStatus.OK).body(eventRepository.allEvents());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetching total events");
		}
	}

	@Override
	public ResponseEntity<Object> totalKidsByEvent(int eventId) {
		try 
		{
			Event event = eventRepository.findByEventId(eventId);
			return ResponseEntity.status(HttpStatus.OK).body(event.getKids().size());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetching total Kids by event");
		}
	}
}
