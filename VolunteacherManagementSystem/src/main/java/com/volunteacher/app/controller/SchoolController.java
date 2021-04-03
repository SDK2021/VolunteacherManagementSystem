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

import com.volunteacher.app.model.Requirement;
import com.volunteacher.app.model.School;
import com.volunteacher.app.service.interfaces.SchoolService;

@RestController
@RequestMapping(path = "/vms")
@CrossOrigin(origins="http://localhost:4200")
public class SchoolController {
	
	@Autowired
	SchoolService schoolService;
	
	@GetMapping("/schools")
	public ResponseEntity<Object> getSchoolList()
	{
		return schoolService.schoolList();
	}
	
	@PostMapping("/schools")
	public ResponseEntity<Object> addSchool(@RequestBody School school)
	{
		return schoolService.addSchool(school);
	}
	
	@GetMapping("/schools/{id}")
	public ResponseEntity<Object> getSchool(@PathVariable int id)
	{
		return schoolService.schoolById(id);
	}
	
	@PutMapping("/schools/{id}")
	public ResponseEntity<Object> updateSchool(@RequestBody School school,@PathVariable int id)
	{
		return schoolService.updateSchool(school, id);
	}
	
	@DeleteMapping("/schools/{id}")
	public ResponseEntity<Object> deleteSchool(@PathVariable int id)
	{
		return schoolService.deleteSchool(id);
	}
	
	@GetMapping("/requirements")
	public ResponseEntity<Object> getRequirementList()
	{
		return schoolService.requirementList();
	}
	
	@PostMapping("/requirements")
	public ResponseEntity<Object> addRequirement(@RequestBody Requirement requirement)
	{
		return schoolService.addRequirement(requirement);
	}
	
	@PutMapping("/requirements/{id}")
	public ResponseEntity<Object> updaterequirements(@RequestBody Requirement requirement,@PathVariable int id)
	{
		return schoolService.updateRequirement(requirement, id);
	}
	
	@DeleteMapping("/requirements/{id}")
	public ResponseEntity<Object> deleterequirements(@PathVariable int id)
	{
		return schoolService.deleteRequirement(id);
	}
}
