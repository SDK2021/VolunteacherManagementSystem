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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<Object> addProject(@RequestBody Project project, @RequestHeader("volunteacherIds") String vIds[],@RequestHeader("kidsIds") String kIds[])
	{
		System.out.println(vIds);
		return projectService.addProject(project,vIds,kIds);
	}
	
	@GetMapping("/projects")
	public ResponseEntity<Object> getProjectList(@RequestParam("page") int page)
	{
		return projectService.projectList(page);
	}
	
	@GetMapping("/allprojects")
	public ResponseEntity<Object> getAllprojectList()
	{
		return projectService.AllprojectList();
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
	
	@GetMapping("/project-number/{id}")
	public int totalProjectByUser(@PathVariable int id)
	{
		return projectService.TotalNumberProjectByUser(id);
	}
	
	@GetMapping("/projects-list")
	public ResponseEntity<Object> getAllProject()
	{
		return projectService.allProjectList();
	}
	
	@GetMapping("/project-numbers/{id}")
	public ResponseEntity<Object> getAllProjectNumbers(@PathVariable int id)
	{
		return projectService.projectNumbersByUser(id);
	}
	
	@GetMapping("/sessions-projects")
	public ResponseEntity<Object> getAllSessionsByProject(@RequestParam("project") int id)
	{
		return projectService.totalSessionsByProject(id);
	}
	
	@GetMapping("/kids-projects")
	public ResponseEntity<Object> getAllKidsByProject(@RequestParam("project") int id)
	{
		return projectService.totalKidsByProject(id);
	}
	
	@GetMapping("/volunteachers-projects")
	public ResponseEntity<Object> getAllVolunteachersByProject(@RequestParam("project") int id)
	{
		return projectService.totalVolunteachersByProject(id);
	}
}
