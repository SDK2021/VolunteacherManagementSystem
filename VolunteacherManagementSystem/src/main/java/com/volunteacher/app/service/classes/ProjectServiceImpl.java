package com.volunteacher.app.service.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.Project;
import com.volunteacher.app.repository.ProjectRepository;
import com.volunteacher.app.service.interfaces.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Override
	public ResponseEntity<Object> addProject(Project project) 
	{
		try {
			Project saveProject = projectRepository.save(project);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveProject);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on Project Creation");
		}
	}

	@Override
	public ResponseEntity<Object> projectList(int page) 
	{
		try {
			List<Project> projectList = (List<Project>) projectRepository.findAll();
			
			if(projectList.size() < 1)
			{
				throw new ResourceNotFoundException("Project list not found");
			}
			else 
			{
				Pageable pageable = PageRequest.of(page, 5, Sort.by("creationDate").descending());
				Page<Project> pageprojectList = (Page<Project>) projectRepository.findAll(pageable);
				return ResponseEntity.status(HttpStatus.OK).body(pageprojectList.getContent());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch projects");
		}
	}

	@Override
	public ResponseEntity<Object> projectById(int id) 
	{
		try {
			Project project = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project is not found for id: "+id));
			return ResponseEntity.status(HttpStatus.OK).body(project);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch project for id:" +id);
		}
	}

	@Override
	public ResponseEntity<Object> updateProject(Project project, int id) 
	{
		Project updateProject = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project is not found for id: "+id));
		
		updateProject.setProjectName(project.getProjectName());
		updateProject.setProjectData(project.getProjectData());
		updateProject.setStartingDate(project.getStartingDate());
		updateProject.setCreationDate(project.getCreationDate());
		updateProject.setCreationTime(project.getCreationTime());
		updateProject.setUsers(project.getUsers());
		updateProject.setKids(project.getKids());
		updateProject.setEndingDate(project.getCreationDate());
		
		try {
			projectRepository.save(updateProject);
			return ResponseEntity.status(HttpStatus.OK).body(updateProject);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in updating Project for id:" +id);
		}
	}

	@Override
	public ResponseEntity<Object> deleteProject(int id) 
	{
		projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project is not found for id: "+id));
		
		try {
			projectRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Project is deleted for id: "+id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in Deleting Project for id:" +id);
		}
	}

	@Override
	public int TotalNumberProjectByUser(int id) {
			return projectRepository.TotalProjectByUser(id);
	}
}
