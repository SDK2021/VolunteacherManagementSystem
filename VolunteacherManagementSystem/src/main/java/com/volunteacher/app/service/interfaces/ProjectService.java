package com.volunteacher.app.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Project;

public interface ProjectService {
	
	public ResponseEntity<Object> addProject(Project project);
	
	public ResponseEntity<Object> projectList(int page);
	
	public ResponseEntity<Object> projectById(int id);
	
	public ResponseEntity<Object> updateProject(Project project, int id);
	
	public ResponseEntity<Object> deleteProject(int id);
	
	public int TotalNumberProjectByUser(int id);
	
	public ResponseEntity<Object> allProjectList();
	
	public ResponseEntity<Object> projectNumbersByUser(int userId);
	
	public ResponseEntity<Object> totalSessionsByProject(int projectId);
	
	public ResponseEntity<Object> totalKidsByProject(int projectId);
	
	public ResponseEntity<Object> totalVolunteachersByProject(int projectId);
}
