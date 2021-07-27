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
	public ResponseEntity<Object> getSessionReport(@RequestParam("page") int page, @RequestParam("sessionId") int id)
	{
		return sessionService.sessionReportsBySession(page,id);
	}
	
	@DeleteMapping("/session-reports/{id}")
	public ResponseEntity<Object> deleteSessionReport(@PathVariable int id)
	{
		return sessionService.deleteSessionReport(id);
	}
	
	@GetMapping("/total-sessions/{id}")
	public ResponseEntity<Object> getTotalSessionsByUser(@PathVariable int id)
	{
		return sessionService.totalSessionsByUser(id);
	}
	
	@PostMapping("/session-volunteachers")
	public ResponseEntity<Object> addAllSessionVolunteacher(@RequestBody List<User> users,@RequestHeader("sessionId") String sessionId)
	{
		return sessionService.addSessionVolunteachers(users, sessionId);
	}
	
	@GetMapping("/total-sessions")
	public ResponseEntity<Object> getTotalSession()
	{
		return sessionService.getTotalSessions();
	}
	
	@GetMapping("/sessions-requirenments")
	public ResponseEntity<Object> getSessionRequirements()
	{
		return sessionService.getSessionsRequirements();
	}
	
	@GetMapping("/total-sessions-hours")
	public ResponseEntity<Object> getTotalHours()
	{
		return sessionService.getTotalHoursSessions();
	}
	
	@GetMapping("/total-village-sessions")
	public ResponseEntity<Object> getTotalSessionByVillage(@RequestParam("village") int villageId)
	{
		return sessionService.totalSessionsByVillage(villageId);
	}
	
	@GetMapping("/sessions-village")
	public ResponseEntity<Object> getSessionsByVillage(@RequestParam("page") int page,@RequestParam("village") int villageId)
	{
		return sessionService.sessionsByVillage(page, villageId);
	}
	
	@GetMapping("/sessions-project")
	public ResponseEntity<Object> getSessionsByProject(@RequestParam("page") int page,@RequestParam("project") int pid)
	{
		return sessionService.sessionsByProject(page, pid);
	}
}
