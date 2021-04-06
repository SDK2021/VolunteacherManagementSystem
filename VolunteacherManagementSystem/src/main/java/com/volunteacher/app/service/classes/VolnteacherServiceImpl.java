package com.volunteacher.app.service.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.Volunteacher;
import com.volunteacher.app.repository.VolunteacherRepository;
import com.volunteacher.app.service.interfaces.VolunteacherService;

@Service
public class VolnteacherServiceImpl implements VolunteacherService {
	
	@Autowired
	VolunteacherRepository volunteacherRepository;	
	
	@Override
	public ResponseEntity<Object> volunteacherList()
	{
		try {
			List<Volunteacher> volunteacherList = (List<Volunteacher>) volunteacherRepository.findAll();
			
			if(volunteacherList.size() < 1)
				throw new ResourceNotFoundException("Volunteacher List not found");
			
			return ResponseEntity.status(HttpStatus.OK).body(volunteacherList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Volunteacher ");
		}
	}
//	
//	public ResponseEntity<Object> vtByToday()
//	{
//		return volunteacherRepository.findAllByDay();
//	}

	@Override
	public ResponseEntity<Object> addVolunteacher(Volunteacher volunteacher) 
	{
		try {	
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
		Volunteacher updateVolunteacher = volunteacherRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Volunteacher not found for id: " + id));
		
		updateVolunteacher.setDistrict(volunteacher.getDistrict());
		updateVolunteacher.setEducation(volunteacher.getEducation());
		updateVolunteacher.setEmployerName(volunteacher.getEmployerName());
		updateVolunteacher.setEndingDate(volunteacher.getEndingDate());
		updateVolunteacher.setJoiningDate(volunteacher.getJoiningDate());
		updateVolunteacher.setPincode(volunteacher.getPincode());
		updateVolunteacher.setSchool(volunteacher.getSchool());
		updateVolunteacher.setStatus(volunteacher.getStatus());
		updateVolunteacher.setVillage(volunteacher.getVillage());
		updateVolunteacher.setJoiningTime(volunteacher.getJoiningTime());
	
		try {
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
		volunteacherRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Volunteacher not found for id: " + id));
		
		try {
			volunteacherRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Volunteacher Delete for id: "+id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in Deleting Volunteacher for id:" +id);
		}
	}
}
