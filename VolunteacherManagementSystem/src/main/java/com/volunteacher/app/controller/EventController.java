package com.volunteacher.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.volunteacher.app.model.Event;
import com.volunteacher.app.model.Participant;
import com.volunteacher.app.model.User;
import com.volunteacher.app.service.interfaces.EventService;
import com.volunteacher.app.service.interfaces.ParticipantService;

@RestController
@RequestMapping(path = "/vms")
public class EventController {
	
	@Autowired
	EventService eventService;
	
	@Autowired
	ParticipantService participantService;
	
	@PostMapping("/events")
	public ResponseEntity<Object> addEvent(@RequestBody Event event,@RequestHeader("activityIds") String[] ids)
	{
		return eventService.addEvent(event,ids);
	}
	
	@GetMapping("/events/{id}")
	public ResponseEntity<Object> getEvent(@PathVariable int id)
	{
		return eventService.eventById(id);
	}
	
	@GetMapping("/events")
	public ResponseEntity<Object> getEventListByMonthAndYear(@RequestParam("page") int page)
	{
		return eventService.eventList(page);
	}
	
	@GetMapping("/all-events")
	public ResponseEntity<Object> getAllEvents(@RequestParam("page") int page)
	{
		return eventService.getAllEvents(page);
	}
	
	@GetMapping("/total-participate-kids")
	public ResponseEntity<Object> getAllParticipantKidsByEvent(@RequestParam("event") int eventId)
	{
		return eventService.totalKidsByEvent(eventId);
	}
	
	@GetMapping("/total-participant")
	public ResponseEntity<Object> getAllParticipantByEvent(@RequestParam("event") int eventId)
	{
		return participantService.totalParticipantByEvent(eventId);
	}
	
	@GetMapping("/total-participate-volunteachers")
	public ResponseEntity<Object> getAllParticipantVolunteacherByEvent(@RequestParam("event") int eventId,@RequestParam("type") int typeId)
	{
		return participantService.totalVolunteacherParticipantByEvent(eventId, typeId);
	}
	
	@GetMapping("/total-participate-others")
	public ResponseEntity<Object> getAllOtherParticipantByEvent(@RequestParam("event") int eventId)
	{
		return participantService.totalOtherParticipantByEvent(eventId);
	}
	
	@DeleteMapping("/events/{id}")
	public ResponseEntity<Object> deleteEvent(@PathVariable int id)
	{
		return eventService.deleteEvent(id);
	}

	@PutMapping("/events/{id}")
	public ResponseEntity<Object> updateEvent(@RequestBody Event event, @PathVariable int id,@RequestHeader("activityIds") String[] ids)
	{
		return eventService.updateEvent(event, id,ids);
	}
	
	@PostMapping("/participants")
	public ResponseEntity<Object> addParticipant(@RequestBody Participant participant)
	{
		System.out.println(participant);
		return participantService.addParticipant(participant);
	}
	
	@PostMapping("/user-participants")
	public ResponseEntity<Object> addUserParticipant(@RequestBody List<User> users,@RequestParam("event") int eventId)
	{
		System.out.println(users + "Hrllo");
		return participantService.addUserParticipant(users,eventId);
	}
	
	@GetMapping("/participants")
	public ResponseEntity<Object> getParticipantList()
	{
		return participantService.participantList();
	}
	
	@GetMapping("participants/{id}")
	public ResponseEntity<Object> getParticipant(@PathVariable Long id)
	{
		return participantService.participantById(id);
	}
	
	@PostMapping("/kids-participants")
	public ResponseEntity<Object> addAllKidsParticipants(@RequestHeader("kidsIds")String [] kidsIds,@RequestHeader("eventId") String eventId)
	{
		return this.eventService.addKidsParticipants(kidsIds, eventId);
	}
	
	@GetMapping("/total-events")
	public ResponseEntity<Object> getTotalEvents()
	{
		return eventService.getTotalEvents();
	}

}
