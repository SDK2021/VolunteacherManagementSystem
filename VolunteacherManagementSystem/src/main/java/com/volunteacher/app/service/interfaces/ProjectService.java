package com.volunteacher.app.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Project;

public interface ProjectService {
	
	public ResponseEntity<Object> addProject(Project project);
	
	public ResponseEntity<Object> projectList();
	
	public ResponseEntity<Object> projectById(int id);
	
	public ResponseEntity<Object> updateProject(Project project, int id);
	
	public ResponseEntity<Object> deleteProject(int id);
}
