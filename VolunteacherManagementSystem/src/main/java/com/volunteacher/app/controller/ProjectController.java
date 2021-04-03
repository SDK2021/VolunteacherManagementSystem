package com.volunteacher.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.volunteacher.app.model.Project;
import com.volunteacher.app.service.interfaces.ProjectService;

@RestController
@RequestMapping(path = "/vms")
@CrossOrigin(origins="http://localhost:4200")  
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	@PostMapping("/projects")
	public ResponseEntity<Object> addProject(@RequestBody Project project)
	{
		return projectService.addProject(project);
	}
	
	@GetMapping("/projects")
	public ResponseEntity<Object> getProjectList()
	{
		return projectService.projectList();
	}
	
	@GetMapping("/projects/{id}")
	public ResponseEntity<Object> getProject(@PathVariable int id)
	{
		return projectService.projectById(id);
	}
	
	@PutMapping("/projects/{id}")
	public ResponseEntity<Object> updateProject(@RequestBody Project project, @PathVariable int id)
	{
		return projectService.updateProject(project, id);
	}
	
	@DeleteMapping("/projects/{id}")
	public ResponseEntity<Object> deleteProject(@PathVariable int id)
	{
		return projectService.deleteProject(id);
	}
}
