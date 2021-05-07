package com.volunteacher.app.service.classes;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.Activity;
import com.volunteacher.app.repository.ActivityRepository;
import com.volunteacher.app.service.interfaces.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService{
	
	@Autowired 
	ActivityRepository activityRepository;
	
	@Override
	public ResponseEntity<Object> addActivity(Activity activity) 
	{
		try {
			Activity saveActivity = activityRepository.save(activity);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveActivity);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creating activities list");
		}
	}

	@Override
	public ResponseEntity<Object> activitiesList(int page) 
	{
		try {
			Pageable pageable = PageRequest.of(page, 10);
			Page<Activity> activityList = (Page<Activity>) activityRepository.findAll(pageable);
			return ResponseEntity.status(HttpStatus.OK).body(activityList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch activity list");
		}
	}

	@Override
	public ResponseEntity<Object> updateActivity(Activity activity, int id) 
	{
		try {
			Activity updateActivity = activityRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Activity is not found for id: "+ id));
			
			updateActivity.setActivityName(activity.getActivityName());
			updateActivity.setDescription(activity.getActivityName());
			activityRepository.save(updateActivity);
			return ResponseEntity.status(HttpStatus.OK).body(updateActivity);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in updating activity for id:" +id);
		}
		
	}

	@Override
	public ResponseEntity<Object> deleteActivity(int id) 
	{
		try {
			activityRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Activity is not found for id: "+ id));
			activityRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on deleting activity");
		}
	}
}
