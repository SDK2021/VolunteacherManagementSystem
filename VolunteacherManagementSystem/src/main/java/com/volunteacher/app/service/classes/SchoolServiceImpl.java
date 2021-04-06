package com.volunteacher.app.service.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.Requirement;
import com.volunteacher.app.model.School;
import com.volunteacher.app.repository.RequirementRepository;
import com.volunteacher.app.repository.SchoolRepository;
import com.volunteacher.app.service.interfaces.SchoolService;

@Service
public class SchoolServiceImpl implements SchoolService {
	
	@Autowired
	SchoolRepository schoolRepository;
	
	@Autowired
	RequirementRepository requirementRepository;
	
	@Override
	public ResponseEntity<Object> addSchool(School school) 
	{
		try {
			School saveSchool = schoolRepository.save(school);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveSchool);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on School Creation");
		}
	}

	@Override
	public ResponseEntity<Object> schoolList() 
	{
		try {
			List<School> schoolList = (List<School>) schoolRepository.findAll();
			
			if(schoolList.size() < 1)
				throw new ResourceNotFoundException("School list not found");
			
			return ResponseEntity.status(HttpStatus.OK).body(schoolList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch School ");
		}
	}

	@Override
	public ResponseEntity<Object> schoolById(int id) 
	{
		try {
			School school = schoolRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("School is not found for id: "+id));
			return ResponseEntity.status(HttpStatus.OK).body(school);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch School for id: "+ id);
		}
	}

	@Override
	public ResponseEntity<Object> updateSchool(School school, int id) 
	{
		School updateSchool = schoolRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("School is not found for id: "+id));
		
		updateSchool.setName(school.getName());
		updateSchool.setPhoneNumber(school.getPhoneNumber());
		updateSchool.setPincode(school.getPincode());
		updateSchool.setStartingDate(school.getStartingDate());
		updateSchool.setStatus(school.getStatus());
		updateSchool.setStream(school.getStream());
		updateSchool.setTotalLabs(school.getTotalLabs());
		updateSchool.setTotalStudent(school.getTotalStudent());
		updateSchool.setVillage(school.getVillage());
		updateSchool.setRequirements(school.getRequirements());
		
		try {
			schoolRepository.save(updateSchool);
			return ResponseEntity.status(HttpStatus.OK).body(updateSchool);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in updating School for id:" +id);
		}
	}

	@Override
	public ResponseEntity<Object> deleteSchool(int id) 
	{
		schoolRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("School is not found for id: "+id));
		
		try {
			schoolRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("School is deleted for id: "+id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in Deleting School for id:" +id);
		}
	}

	@Override
	public ResponseEntity<Object> addRequirement(Requirement requirement) 
	{
		try {
			Requirement saveRequirement = requirementRepository.save(requirement);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveRequirement);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creating Requirement");
		}
	}

	@Override
	public ResponseEntity<Object> requirementList() 
	{
		try {
			List<Requirement> requirementList = (List<Requirement>) requirementRepository.findAll();
			
			if(requirementList.size() < 1)
				throw new ResourceNotFoundException("Requirement List list not found");
			return ResponseEntity.status(HttpStatus.OK).body(requirementList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch requirement");
		}
	}

	@Override
	public ResponseEntity<Object> updateRequirement(Requirement requirement, int id) 
	{
		Requirement updateRequirement = requirementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Requiremement not found for id:"+id));
		updateRequirement.setRequirement(requirement.getRequirement());
		try {
			requirementRepository.save(updateRequirement);
			return ResponseEntity.status(HttpStatus.OK).body(updateRequirement);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in updating Requirement for id:" +id);
		}
	}

	@Override
	public ResponseEntity<Object> deleteRequirement(int id) 
	{
		requirementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Requirement not found for id:"+id));
		
		try {
			requirementRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Requirement Deleted For id:"+id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in Deleting Requirement for id:" +id);
		}
	}

}	
