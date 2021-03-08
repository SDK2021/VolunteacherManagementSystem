package com.volunteacher.app.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.KidsReport;

public interface KidsReportService {
	
	public ResponseEntity<Object> addKidReport(KidsReport kidsReport);
	
	public List<KidsReport> kidReportList();
	
	public KidsReport KidReport(int id);
	
	public KidsReport kidReportByKid(int id);
	
	public ResponseEntity<Object> updateKidReport(KidsReport kidReport, int id);
	
	public ResponseEntity<Object> deleteKidReport(int id);
}
