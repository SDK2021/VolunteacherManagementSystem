package com.volunteacher.app.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Session;
import com.volunteacher.app.model.SessionReport;

public interface SessionService {
	
	public ResponseEntity<Object> addSession(Session session);
	
	public ResponseEntity<Object> sessionList();
	
	public ResponseEntity<Object> sessionById(Long id);
	
	public ResponseEntity<Object> updateSession(Session session, Long id);
	
	public ResponseEntity<Object> deleteSession(Long id);
	
	public ResponseEntity<Object> addSessionReport(SessionReport sessionReport);
	
	public ResponseEntity<Object> sessionReportList();
	
	public ResponseEntity<Object> sessionReport(int id);
	
	public ResponseEntity<Object> deleteSessionReport(int id);
}
