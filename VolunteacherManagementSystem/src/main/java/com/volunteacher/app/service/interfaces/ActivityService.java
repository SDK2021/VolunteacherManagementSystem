package com.volunteacher.app.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Activity;

public interface ActivityService 
{
	public ResponseEntity<Object> addActivity(Activity activities);
	
	public ResponseEntity<Object> activitiesList(int page);

	public ResponseEntity<Object> updateActivity(Activity activity, int id);
	
	public ResponseEntity<Object> deleteActivity(int id);
	
	public ResponseEntity<Object> allActivitiesList();
}
