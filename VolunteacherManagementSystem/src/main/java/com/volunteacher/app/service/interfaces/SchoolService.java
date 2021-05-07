package com.volunteacher.app.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.School;

public interface SchoolService {
	
	public ResponseEntity<Object> addSchool(School school);
	
	public ResponseEntity<Object> schoolList(int page);
	
	public ResponseEntity<Object> schoolById(int id);
	
	public ResponseEntity<Object> updateSchool(School school, int id);
	
	public ResponseEntity<Object> deleteSchool(int id);
	
}
