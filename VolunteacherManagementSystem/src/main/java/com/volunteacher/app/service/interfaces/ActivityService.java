package com.volunteacher.app.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Activity;

public interface ActivityService 
{
	public ResponseEntity<Object> addActivity(List<Activity> activities);
	
	public List<Activity> activitiesList();

}
