package com.volunteacher.app.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Report;

public interface ReportService {
	
	public ResponseEntity<Object> addReport(Report report);
	
	public ResponseEntity<Object> reportList();
	
	public ResponseEntity<Object> updateReport(Report report, int id);
	
	public ResponseEntity<Object> reportById(int id);
	
	public ResponseEntity<Object> deleteReport(int id);
	
	public ResponseEntity<Object> reportByCurrentYear(int year);
}
