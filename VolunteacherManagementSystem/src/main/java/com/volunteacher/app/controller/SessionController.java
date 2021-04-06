package com.volunteacher.app.controller;

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

import com.volunteacher.app.model.Session;
import com.volunteacher.app.model.SessionReport;
import com.volunteacher.app.service.interfaces.SessionService;

@RestController
@RequestMapping(path = "/vms")
@CrossOrigin(origins="http://localhost:4200")
public class SessionController {
	
	@Autowired
	SessionService sessionService;
	
	@PostMapping("/sessions")
	public ResponseEntity<Object> addSession(@RequestBody  Session session)
	{
		return sessionService.addSession(session);
	}
	
	@GetMapping("/sessions")
	public ResponseEntity<Object> getSessionList()
	{
		return sessionService.sessionList();
	}
	
	@GetMapping("/sessions/{id}")
	public ResponseEntity<Object> getSession(@PathVariable Long id)
	{
		return sessionService.sessionById(id);
	}
	
	@PutMapping("/sessions/{id}")
	public ResponseEntity<Object> updateSessions(@RequestBody Session session, @PathVariable Long id)
	{
		return sessionService.updateSession(session, id);
	}
	
	@DeleteMapping("/sessions/{id}")
	public ResponseEntity<Object> deleteSession(@PathVariable Long id)
	{
		return sessionService.deleteSession(id);
	}
	
	@PostMapping("/session-reports")
	public ResponseEntity<Object> addSessionReport(@RequestBody SessionReport report)
	{
		return sessionService.addSessionReport(report);
	}
	
	@GetMapping("/session-reports")
	public ResponseEntity<Object> getSessionReportList()
	{
		return sessionService.sessionReportList();
	}
	
	@GetMapping("/session-reports/{id}")
	public ResponseEntity<Object> getSessionReport(@PathVariable int id)
	{
		return sessionService.sessionReport(id);
	}
	
	@DeleteMapping("/session-reports/{id}")
	public ResponseEntity<Object> deleteSessionReport(@PathVariable int id)
	{
		return sessionService.deleteSessionReport(id);
	}
}
