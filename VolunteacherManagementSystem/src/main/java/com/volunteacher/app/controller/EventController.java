package com.volunteacher.app.controller;

import java.util.List;

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

import com.volunteacher.app.model.Event;
import com.volunteacher.app.model.Participant;
import com.volunteacher.app.service.interfaces.EventService;
import com.volunteacher.app.service.interfaces.ParticipantService;

@RestController
@RequestMapping(path = "/vms")
@CrossOrigin(origins="http://localhost:4200")  
public class EventController {
	
	@Autowired
	EventService eventService;
	
	@Autowired
	ParticipantService participantService;
	
	@PostMapping("/events")
	public ResponseEntity<Object> addEvent(@RequestBody Event event)
	{
		return eventService.addEvent(event);
	}
	
	@GetMapping("/events/")
	public List<Event> eventList()
	{
		return eventService.eventList();
	}
	
	@DeleteMapping("/events/{id}")
	public ResponseEntity<Object> deleteEvent(@PathVariable int id)
	{
		return eventService.deleteEvent(id);
	}

	@PutMapping("/events/{id}")
	public ResponseEntity<Object> updateEvent(@RequestBody Event event, @PathVariable int id)
	{
		return eventService.updateEvent(event, id);
	}
	
	@PostMapping("/participants")
	public ResponseEntity<Object> addParticipant(@RequestBody Participant participant)
	{
		return participantService.addParticipant(participant);
	}
	
	@GetMapping("/participants/")
	public List<Participant> participantList()
	{
		return participantService.participantList();
	}
	
	@GetMapping("participants/{id}")
	public Participant Participant(@PathVariable Long id)
	{
		return participantService.participant(id);
	}
	
}
