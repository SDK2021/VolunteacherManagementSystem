package com.volunteacher.app.service.interfaces;

import org.springframework.http.ResponseEntity;

public interface ExcelService {
	
	public ResponseEntity<Object> downloadKids();
	
	public ResponseEntity<Object> downloadVolunteachers();
	
	public ResponseEntity<Object> downloadEvents();
	
	public ResponseEntity<Object> downloadSchools();
	
	public ResponseEntity<Object> downloadSessions();
}
