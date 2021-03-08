package com.volunteacher.app.service.classes;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.Activity;
import com.volunteacher.app.repository.ActivityRepository;
import com.volunteacher.app.service.interfaces.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService{

	ActivityRepository activityRepository;
	
	@Override
	public ResponseEntity<Object> addActivity(List<Activity> activities) {
		
		try {
			List<Activity> saveActivity = (List<Activity>) activityRepository.saveAll(activities);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveActivity);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creating activities list");
		}
	}

	@Override
	public List<Activity> activitiesList() {
		
		List<Activity> activityList = (List<Activity>) activityRepository.findAll();
		
		if(activityList.size() < 1)
			throw new ResourceNotFoundException("Activity list not found");
		
		return activityList;
	}
	
	
}
