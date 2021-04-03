package com.volunteacher.app.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.KidsReport;

public interface KidsReportService {
	
	public ResponseEntity<Object> addKidReport(KidsReport kidsReport);
	
	public ResponseEntity<Object> kidReportList();
	
	public ResponseEntity<Object> kidReportById(int id);
	
	public ResponseEntity<Object> kidReportByKid(int id);
	
	public ResponseEntity<Object> updateKidReport(KidsReport kidReport, int id);
	
	public ResponseEntity<Object> deleteKidReport(int id);
}
