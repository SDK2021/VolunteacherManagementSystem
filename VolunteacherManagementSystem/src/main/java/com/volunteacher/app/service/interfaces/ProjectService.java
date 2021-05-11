package com.volunteacher.app.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Kid;
import com.volunteacher.app.model.Project;
import com.volunteacher.app.model.User;

public interface ProjectService {
	
	public ResponseEntity<Object> addProject(Project project,String[] vIds,String[] kIds);
	
	public ResponseEntity<Object> projectList(int page);
	
	public ResponseEntity<Object> AllprojectList(); 
	
	public ResponseEntity<Object> projectById(int id);
	
	public ResponseEntity<Object> updateProject(Project project, int id,String[] vIds,String[] kIds);
	
	public ResponseEntity<Object> deleteProject(int id);
	
	public int TotalNumberProjectByUser(int id);
	
	public ResponseEntity<Object> projectNumbersByUser(int userId);
	
	public ResponseEntity<Object> totalSessionsByProject(int projectId);
	
	public ResponseEntity<Object> totalKidsByProject(int projectId);
	
	public ResponseEntity<Object> totalVolunteachersByProject(int projectId);
	
	public ResponseEntity<Object> totalEventByProject(int projectId);
	
	public List<User> createVolunteachersList(String[] ids);
	
	public List<Kid> createKidsList(String[] ids);
	
	public ResponseEntity<Object> getReaminingKids(int projectId);
	
	public ResponseEntity<Object> getReaminingUser(int projectId);
	
}
