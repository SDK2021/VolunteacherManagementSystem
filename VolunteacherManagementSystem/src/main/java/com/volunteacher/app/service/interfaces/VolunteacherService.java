package com.volunteacher.app.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Volunteacher;

public interface VolunteacherService {
	
	public ResponseEntity<Object> addVolunteacher(Volunteacher volunteacher);
	
	public List<Volunteacher> volunteacherList();
	
	public Volunteacher volunteacherById(int id);
	
	public ResponseEntity<Object> updateVolunteacher(Volunteacher volunteacher, int id);
	
	public ResponseEntity<Object> deleteVolunteacher(int id);
	
	public List<Volunteacher> vtByToday();
}
