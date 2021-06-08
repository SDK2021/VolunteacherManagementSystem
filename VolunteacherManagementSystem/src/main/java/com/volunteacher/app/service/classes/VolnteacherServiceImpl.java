package com.volunteacher.app.service.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.Session;
import com.volunteacher.app.model.User;
import com.volunteacher.app.model.Volunteacher;
import com.volunteacher.app.repository.SessionRepository;
import com.volunteacher.app.repository.VolunteacherRepository;
import com.volunteacher.app.service.interfaces.VolunteacherService;

@Service
public class VolnteacherServiceImpl implements VolunteacherService {
	
	@Autowired
	VolunteacherRepository volunteacherRepository;	
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	SessionRepository sessionRepository;
	
	@Override
	public ResponseEntity<Object> volunteacherList(int page)
	{
		try {
			Pageable pageable = PageRequest.of(page, 9);
			Page<Volunteacher> volunteacherList = (Page<Volunteacher>) volunteacherRepository.findAll(pageable);
			return ResponseEntity.status(HttpStatus.OK).body(volunteacherList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Volunteacher ");
		}
	}
	
	public List<Volunteacher> vtByToday()
	{
		return volunteacherRepository.findAllByDay();
	}

	@Override
	public ResponseEntity<Object> addVolunteacher(Volunteacher volunteacher) 
	{
		try {	
			volunteacher.getUser().setPassword(passwordEncoder.encode(volunteacher.getUser().getPassword()));
			Volunteacher saveVolunteacher = volunteacherRepository.save(volunteacher);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveVolunteacher);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creation Volunteacher");
			
		}	
	}

	@Override
	public ResponseEntity<Object> volunteacherById(int id) 
	{
		try {
			Volunteacher volunteacher = volunteacherRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Volunteacher not found for id: " + id));
			return ResponseEntity.status(HttpStatus.OK).body(volunteacher);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Volunteacher for id: "+ id);
		}
	}

	@Override
	public ResponseEntity<Object> updateVolunteacher(Volunteacher volunteacher, int id) 
	{
		try {
			Volunteacher updateVolunteacher = volunteacherRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Volunteacher not found for id: " + id));
			
			updateVolunteacher.setDistrict(volunteacher.getDistrict());
			updateVolunteacher.setEducation(volunteacher.getEducation());
			updateVolunteacher.setEmployerName(volunteacher.getEmployerName());			
			updateVolunteacher.setJoiningDate(volunteacher.getJoiningDate());
			updateVolunteacher.setPincode(volunteacher.getPincode());
			updateVolunteacher.setSchool(volunteacher.getSchool());
			updateVolunteacher.setStatus(volunteacher.getStatus());
			updateVolunteacher.setVillage(volunteacher.getVillage());
			updateVolunteacher.setUser(volunteacher.getUser());
			volunteacherRepository.save(updateVolunteacher);
			
			return ResponseEntity.status(HttpStatus.OK).body(updateVolunteacher);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in updating Volunteacher for id:" +id);
		}
	}

	@Override
	public ResponseEntity<Object> deleteVolunteacher(int id) 
	{	
		try {
			Volunteacher volunteacher= volunteacherRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Volunteacher not found for id: " + id));
			volunteacherRepository.deleteVolunteacherProjects(volunteacher.getUser().getUserId());
			System.out.println(volunteacher);
			volunteacherRepository.deleteVolunteacherSessions(volunteacher.getUser().getUserId());
			volunteacherRepository.deleteVolunteacherEvents(id);
			volunteacherRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in Deleting Volunteacher for id:" +id);
		}
	}

	@Override
	public ResponseEntity<Object> volunteacherByUserId(long id) {
		try {
			Volunteacher volunteacher = volunteacherRepository.findByUserUserId(id);
			return ResponseEntity.status(HttpStatus.OK).body(volunteacher);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetching volunteacher by user Id");
		}
	}

	@Override
	public ResponseEntity<Object> getTotalVolunteacher() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(volunteacherRepository.allVolunteacher());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetching total volunteacher");
		}
	}

	@Override
	public ResponseEntity<Object> getNewVolunteachers(int page) {
		try {
			Pageable pageable = PageRequest.of(page, 5,Sort.by("joining_date").descending());
			Page<Volunteacher> volunteacherList = (Page<Volunteacher>) volunteacherRepository.newVolunteachers(pageable);
			return ResponseEntity.status(HttpStatus.OK).body(volunteacherList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch new Volunteachers");
		}
	}
	
	@Override
	public ResponseEntity<Object> getAllNewVolunteachers() {
		try {
			List<Volunteacher> volunteacherList = (List<Volunteacher>) volunteacherRepository.newAllVolunteachers();
			return ResponseEntity.status(HttpStatus.OK).body(volunteacherList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch new Volunteachers");
		}
	}
	
	@Override
	public int getVolunteacherStatus(int vid) {
		Volunteacher volunteacher = volunteacherRepository.findById(vid).orElseThrow(()->new ResourceNotFoundException("Error"));
		int count = 0;
		List<Session> sessions  = this.sessionRepository.sessionsForVolunteacher(volunteacher.getJoiningDate());
		System.out.println(this.sessionRepository.sessionsForVolunteacher(volunteacher.getJoiningDate()));
		for (Session session : sessions) {
			session.getUsers();
			for (User user : session.getUsers()) {
				if(user.getUserId() == volunteacher.getUser().getUserId())
				{
					count++;
					break;
				}
			}
		}
		System.out.println(count);
		if(count < sessions.size()/2)
		{
			return 2;
		}
		else 
		{
			return 1;
		}
	}

}
