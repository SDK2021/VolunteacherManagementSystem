package com.volunteacher.app.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Volunteacher;

public interface VolunteacherService {
	
	public ResponseEntity<Object> addVolunteacher(Volunteacher volunteacher);
	
	public ResponseEntity<Object> volunteacherList(int page);
	
	public ResponseEntity<Object> volunteacherById(int id);
	
	public ResponseEntity<Object> volunteacherByUserId(long id);
	
	public ResponseEntity<Object> updateVolunteacher(Volunteacher volunteacher, int id);
	
	public ResponseEntity<Object> deleteVolunteacher(int id);
	
	public List<Volunteacher> vtByToday();
	
	public int getVolunteacherStatus(int vid); 
	
	public ResponseEntity<Object> getTotalVolunteacher(); 
	
	public ResponseEntity<Object> getNewVolunteachers(int page);
	
	public ResponseEntity<Object> getAllNewVolunteachers();
	
	public ResponseEntity<Object> getAllVolunteachersByStatus(int page,int id);
	
	public ResponseEntity<Object> getAllVolunteachersByVillage(int page,int id);
	
	public ResponseEntity<Object> getAllVolunteachersByUserType(int page,int type);
	
	public ResponseEntity<Object> getAllVolunteachersByProject(int page,int project);
}
