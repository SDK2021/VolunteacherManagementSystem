package com.volunteacher.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.volunteacher.app.model.Activity;
import com.volunteacher.app.service.interfaces.ActivityService;

@RestController
@RequestMapping(path = "/vms")
public class ActivityController {
	
	@Autowired
	ActivityService activityService;
	
	
	@GetMapping("/all-activities")
	public ResponseEntity<Object> getAllActivities()
	{
		return activityService.allActivitiesList();
	}
	
	@GetMapping("/activities")
	public ResponseEntity<Object> getAllActivities(@RequestParam("page") int page)
	{
		return activityService.activitiesList(page);
	}
	
	@PostMapping("/activities")
	public ResponseEntity<Object> addActivity(@RequestBody Activity activity)
	{
		return activityService.addActivity(activity);
	}
	
	@PutMapping("/activities/{id}")
	public ResponseEntity<Object> updateActivity(@RequestBody Activity activity, @PathVariable int id)
	{
		return activityService.updateActivity(activity, id);
	}
	
	@DeleteMapping("/activities/{id}")
	public ResponseEntity<Object> deleteActivity(@PathVariable int id)
	{
		return activityService.deleteActivity(id);
	}
	
	@GetMapping("/activities/{id}")
	public ResponseEntity<Object> getActivity(@PathVariable int id)
	{
		return activityService.getActivityById(id);
	}
}
