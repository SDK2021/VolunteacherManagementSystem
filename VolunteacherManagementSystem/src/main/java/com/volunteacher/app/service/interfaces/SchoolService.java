package com.volunteacher.app.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Requirement;
import com.volunteacher.app.model.School;

public interface SchoolService {
	
	public ResponseEntity<Object> addSchool(School school);
	
	public List<School> schoolList();
	
	public School schoolById(int id);
	
	public ResponseEntity<Object> updateSchool(School school, int id);
	
	public ResponseEntity<Object> deleteSchool(int id);
	
	public ResponseEntity<Object> addRequirement(Requirement requirement);
	
	public List<Requirement> requirementList();
	
	public ResponseEntity<Object> deleteRequirement(int id);
}
