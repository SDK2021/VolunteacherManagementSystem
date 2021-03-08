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
			ApplicantRequest addRequest = applicantRequestRepository.save(request);
			return ResponseEntity.status(HttpStatus.CREATED).body(addRequest);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creating applicant Request");
		}
	}
	
	@Override
	public List<ApplicantRequest> requestList()
	{
		List<ApplicantRequest> requestList = (List<ApplicantRequest>) applicantRequestRepository.findAll();
		
		if (requestList.size() < 2) 
			throw new ResourceNotFoundException("Applicant Request List Not found");
		
		return requestList;
	}
	
	@Override
	public ApplicantRequest requestById(int id)
	{
		ApplicantRequest request = applicantRequestRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Request bot found for id: "+ id));
		return request;
	}
	
	@Override
	public ResponseEntity<Object> deleteRequest(int id)
	{
		applicantRequestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Request Not foubd for Id: "+id));
		applicantRequestRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Request deleted for id:" + id);
	}
	
}
