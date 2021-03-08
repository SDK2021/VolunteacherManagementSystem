package com.volunteacher.app.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Project;

public interface ProjectService {
	
	public ResponseEntity<Object> addProject(Project project);
	
	public List<Project> projectList();
	
	public Project project(int id);
	
	public ResponseEntity<Object> updateProject(Project project, int id);
	
	public ResponseEntity<Object> deleteProject(int id);
}
