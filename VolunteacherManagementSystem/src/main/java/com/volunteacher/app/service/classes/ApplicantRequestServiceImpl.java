package com.volunteacher.app.service.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.ApplicantRequest;
import com.volunteacher.app.repository.ApplicantRequestRepository;
import com.volunteacher.app.service.interfaces.ApplicantRequestService;

@Service
public class ApplicantRequestServiceImpl implements ApplicantRequestService {
	
	@Autowired
	ApplicantRequestRepository applicantRequestRepository;
	
	@Override
	public ResponseEntity<Object> addRequest(ApplicantRequest request)
	{
		try {
			ApplicantRequest saveRequest = applicantRequestRepository.save(request);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveRequest);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creating applicant Request");
		}
	}
	
	@Override
	public ResponseEntity<Object> requestList()
	{
		try {
			List<ApplicantRequest> requestList = (List<ApplicantRequest>) applicantRequestRepository.findAll();
			return ResponseEntity.status(HttpStatus.OK).body(requestList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Applicant request list");
		}
	}
	
	@Override
	public ResponseEntity<Object> requestById(int id)
	{
		try {
			ApplicantRequest request = applicantRequestRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Applicant Request not found for id: "+ id));
			return ResponseEntity.status(HttpStatus.OK).body(request);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Applicant request of id:" +id);
		}
	}
	
	@Override
	public ResponseEntity<Object> deleteRequest(int id)
	{
		applicantRequestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Applicant Request Not found for Id: "+id));
		
		try {
			applicantRequestRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Applicant deleted for id:" + id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in Deleting Applicant Request for id:" +id);
		}
	}
}
