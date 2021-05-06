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

import com.volunteacher.app.model.Session;
import com.volunteacher.app.model.SessionReport;
import com.volunteacher.app.model.User;
import com.volunteacher.app.service.interfaces.SessionService;

@RestController
@RequestMapping(path = "/vms")
public class SessionController {
	
	@Autowired
	SessionService sessionService;
	
	@PostMapping("/sessions")
	public ResponseEntity<Object> addSession(@RequestBody  Session session)
	{
		return sessionService.addSession(session);
	}
	
	@GetMapping("/sessions")
	public ResponseEntity<Object> getSessionList(@RequestParam("page") int page, @RequestParam("month") int month, @RequestParam("year") int year)
	{
		return sessionService.sessionList(page,month, year);
	}
	
	@GetMapping("/all-sessions")
	public ResponseEntity<Object> getAllSessionList(@RequestParam("page") int page)
	{
		return sessionService.allSessionList(page);
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
	public ResponseEntity<Object> getSessionReport(@RequestParam("page") int page, @PathVariable int id)
	{
		return sessionService.sessionReportsBySession(page,id);
	}
	
	@DeleteMapping("/session-reports/{id}")
	public ResponseEntity<Object> deleteSessionReport(@PathVariable int id)
	{
		return sessionService.deleteSessionReport(id);
	}
	
	@GetMapping("/total-sessions/{id}")
	public ResponseEntity<Object> getTotalSessions(@PathVariable int id)
	{
		return sessionService.totalSessionsByUser(id);
	}
	
	@PostMapping("/session-volunteachers")
	public ResponseEntity<Object> addAllSessionVolunteacher(@RequestBody List<User> users,@RequestHeader("sessionId") String sessionId)
	{
		return sessionService.addSessionVolunteachers(users, sessionId);
	}
	
	@GetMapping("/total-sessions")
	public ResponseEntity<Object> getTotalKids()
	{
		return sessionService.getTotalSessions();
	}
	
	@GetMapping("/sessions-requirements")
	public ResponseEntity<Object> getSessionRequirements()
	{
		return sessionService.getSessionsRequirements();
	}
}
