package com.volunteacher.app.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Volunteacher;

public interface VolunteacherService {
	
	public ResponseEntity<Object> addVolunteacher(Volunteacher volunteacher);
	
	public ResponseEntity<Object> volunteacherList();
	
	public ResponseEntity<Object> volunteacherById(int id);
	
	public ResponseEntity<Object> volunteacherByUserId(long id);
	
	public ResponseEntity<Object> updateVolunteacher(Volunteacher volunteacher, int id);
	
	public ResponseEntity<Object> deleteVolunteacher(int id);
	
	public ResponseEntity<Object> vtByToday();
}
