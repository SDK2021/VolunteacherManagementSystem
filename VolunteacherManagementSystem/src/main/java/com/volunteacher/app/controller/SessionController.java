package com.volunteacher.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("/session")
	public ResponseEntity<Object> addSession(@RequestBody  Session session)
	{
		return sessionService.addSession(session);
	}
	
	@GetMapping("/sessions/")
	public List<Session> sessionList()
	{
		return sessionService.sessionList();
	}
	
	@GetMapping("/session/{id}")
	public Session sessionById(@PathVariable Long id)
	{
		return sessionService.sessionById(id);
	}
	
	@DeleteMapping("/session/{id}")
	public ResponseEntity<Object> deleteSession(@PathVariable Long id)
	{
		return sessionService.deleteSession(id);
	}
	
	@PostMapping("/sessionReport")
	public ResponseEntity<Object> adddSessionReport(SessionReport report)
	{
		return sessionService.addSessionReport(report);
	}
	
	@GetMapping("/sessionReports/")
	public List<SessionReport> sessionReportList()
	{
		return sessionService.sessionReportList();
	}
	
	@GetMapping("/sessionReport/{id}")
	public SessionReport sessionReport(@PathVariable int id)
	{
		return sessionService.sessionReport(id);
	}
	
	@DeleteMapping("/sessionReport/{id}")
	public ResponseEntity<Object> deleteSessionReport(@PathVariable int id)
	{
		return sessionService.deleteSessionReport(id);
	}
}
