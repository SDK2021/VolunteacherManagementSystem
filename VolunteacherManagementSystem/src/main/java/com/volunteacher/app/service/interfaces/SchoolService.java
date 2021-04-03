package com.volunteacher.app.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Requirement;
import com.volunteacher.app.model.School;

public interface SchoolService {
	
	public ResponseEntity<Object> addSchool(School school);
	
	public ResponseEntity<Object> schoolList();
	
	public ResponseEntity<Object> schoolById(int id);
	
	public ResponseEntity<Object> updateSchool(School school, int id);
	
	public ResponseEntity<Object> deleteSchool(int id);
	
	public ResponseEntity<Object> addRequirement(Requirement requirement);
	
	public ResponseEntity<Object> requirementList();
	
	public ResponseEntity<Object> updateRequirement(Requirement requirement, int id);
	
	public ResponseEntity<Object> deleteRequirement(int id);
	
	
}
