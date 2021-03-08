package com.volunteacher.app.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Session;
import com.volunteacher.app.model.SessionReport;

public interface SessionService {
	
	public ResponseEntity<Object> addSession(Session session);
	
	public List<Session> sessionList();
	
	public Session sessionById(Long id);
	
	public ResponseEntity<Object> updateSession(Session session, Long id);
	
	public ResponseEntity<Object> deleteSession(Long id);
	
	public ResponseEntity<Object> addSessionReport(SessionReport sessionReport);
	
	public List<SessionReport> sessionReportList();
	
	public SessionReport sessionReport(int id);
	
	public ResponseEntity<Object> deleteSessionReport(int id);
}
