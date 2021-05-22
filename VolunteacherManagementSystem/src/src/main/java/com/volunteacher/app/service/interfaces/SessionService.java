package com.volunteacher.app.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Session;
import com.volunteacher.app.model.SessionReport;
import com.volunteacher.app.model.User;

public interface SessionService {
	
	public ResponseEntity<Object> addSession(Session session);
	
	public ResponseEntity<Object> sessionList(int page,int month,int year);
	
	public ResponseEntity<Object> allSessionList(int page);
	
	public ResponseEntity<Object> sessionById(Long id);
	
	public ResponseEntity<Object> updateSession(Session session, Long id);
	
	public ResponseEntity<Object> deleteSession(Long id);
	
	public ResponseEntity<Object> addSessionReport(SessionReport sessionReport);
	
	public ResponseEntity<Object> sessionReportList();
	
	public ResponseEntity<Object> getSessionsRequirements();
	
	public ResponseEntity<Object> sessionReportsBySession(int page,long id);
	
	public ResponseEntity<Object> totalSessionsByUser(int id);
	
	public ResponseEntity<Object> sessionReport(int id);
	
	public ResponseEntity<Object> deleteSessionReport(int id);
	
	public ResponseEntity<Object> addSessionVolunteachers(List<User> users,String sessionId);
	
	public ResponseEntity<Object> getTotalSessions();
	
	public ResponseEntity<Object> getTotalHoursSessions();
	
	public ResponseEntity<Object> totalSessionsByVillage(int villageId);
}
