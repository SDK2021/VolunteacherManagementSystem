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
	public List<Volunteacher> volunteacherList()
	{
		List<Volunteacher> volunteacherList = (List<Volunteacher>) volunteacherRepository.findAll();
		
		if(volunteacherList.size() < 1)
			throw new ResourceNotFoundException("Volunteacher List not found");
		
		return volunteacherList;
	}
	
	public List<Volunteacher> vtByToday()
	{
		return volunteacherRepository.findAllByDay();
	}

	@Override
	public ResponseEntity<Object> addVolunteacher(Volunteacher volunteacher) {
		
		try {	
			Volunteacher addVolunteacher = volunteacherRepository.save(volunteacher);
			return ResponseEntity.status(HttpStatus.CREATED).body(addVolunteacher);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creation Volunteacher");
			
		}	
	}

	@Override
	public Volunteacher volunteacherById(int id) {
		
		Volunteacher volunteacher = volunteacherRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Volunteacher not found for id: " + id));
		return volunteacher;
	}

	@Override
	public ResponseEntity<Object> updateVolunteacher(Volunteacher volunteacher, int id) {
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
		
		volunteacherRepository.save(updateVolunteacher);
		return ResponseEntity.status(HttpStatus.OK).body(updateVolunteacher);
	}

	@Override
	public ResponseEntity<Object> deleteVolunteacher(int id) {
		volunteacherRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Volunteacher not found for id: " + id));
		volunteacherRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Volunteacher Delete for id: "+id);
	}
}
