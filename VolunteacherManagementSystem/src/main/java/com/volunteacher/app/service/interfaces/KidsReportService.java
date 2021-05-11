package com.volunteacher.app.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.KidsReport;

public interface KidsReportService {
	
	public ResponseEntity<Object> addKidReport(KidsReport kidsReport);
	
	public ResponseEntity<Object> kidReportList();
	
	public ResponseEntity<Object> kidReportById(int id);
	
	public ResponseEntity<Object> kidReportByKid(Long id);
	
	public ResponseEntity<Object> updateKidReport(KidsReport kidReport, int id);
	
	public ResponseEntity<Object> deleteKidReport(int id);
	
	public ResponseEntity<Object> kidReportByYear(Long kid,int year);
}
