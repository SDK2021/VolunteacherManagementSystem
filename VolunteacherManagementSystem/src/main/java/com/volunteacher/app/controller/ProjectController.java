package com.volunteacher.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("/projects/")
	public List<Project> projectList()
	{
		return projectService.projectList();
	}
	
	@GetMapping("/projects/{id}")
	public Project project(@PathVariable int id)
	{
		return projectService.project(id);
	}
	
}
