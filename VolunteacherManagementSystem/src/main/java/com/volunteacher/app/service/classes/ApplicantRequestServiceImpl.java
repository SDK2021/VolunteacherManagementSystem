package com.volunteacher.app.service.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.ApplicantRequest;
import com.volunteacher.app.repository.ApplicantRequestRepository;
import com.volunteacher.app.service.interfaces.ApplicantRequestService;
import com.volunteacher.app.service.interfaces.EmailService;
import com.volunteacher.app.service.interfaces.UserService;

@Service
public class ApplicantRequestServiceImpl implements ApplicantRequestService {
	
	@Autowired
	ApplicantRequestRepository applicantRequestRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	EmailService emailService;
	
	@Override
	public ResponseEntity<Object> addRequest(ApplicantRequest request)
	{
		
		if((userService.userByEmail(request.getEmailId()).getBody() != null) || (this.requestByEmail(request.getEmailId()).getBody() != null))
		{
			System.out.println(userService.userByEmail(request.getEmailId()));
			//throw new EmailAlreadyExistException("You email id already exist");
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
		if(userService.userByPhoneNumber(request.getPhoneNumber()).getBody() != null || (this.requestByPhoneNumber(request.getPhoneNumber()).getBody() != null))
		{
			//throw new EmailAlreadyExistException("You email id already exist");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		try {
			ApplicantRequest saveRequest = applicantRequestRepository.save(request);
			this.emailService.registerSuccessfullyMail(request.getEmailId(), request.getName());
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
//			Sort sort = Sort.by(
//					Sort.Order.desc("requestDate"),
//					Sort.Order.asc("name")	
//			);
			Pageable pageable = PageRequest.of(0, 5,Sort.by("requestDate"));
			Page<ApplicantRequest> requestList = (Page<ApplicantRequest>) applicantRequestRepository.findAll(pageable);
			return ResponseEntity.status(HttpStatus.OK).body(requestList.getContent());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Applicant request list");
		}
	}
	
	@Override
	public ApplicantRequest requestById(int id)
	{
		try {
			ApplicantRequest request = applicantRequestRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Applicant Request not found for id: "+ id));
			return request;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public ResponseEntity<Object> deleteRequest(int id)
	{
		applicantRequestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Applicant Request Not found for Id: "+id));
		
		try {
			applicantRequestRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in Deleting Applicant Request for id:" +id);
		}
	}
	
	@Override
	public ResponseEntity<Object> requestByEmail(String email) {
		System.out.println(email);
		try {
			ApplicantRequest request = applicantRequestRepository.findByEmailId(email);
			return ResponseEntity.status(HttpStatus.OK).body(request);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetching Request by Email");
		}
	}

	@Override
	public ResponseEntity<Object> requestByPhoneNumber(String number) {
		System.out.println(number);
		try {
			ApplicantRequest request = applicantRequestRepository.findByPhoneNumber(number);
			return ResponseEntity.status(HttpStatus.OK).body(request);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetching Request by Phonenumber");
		}
	}
	
	@Override
	public boolean successRequest(int requestId) {
		try {
			ApplicantRequest request = applicantRequestRepository.findById(requestId).orElseThrow(()->new ResourceNotFoundException("Error on sending zccepted mail"));;
			request.setStatus(1);
			applicantRequestRepository.save(request);
			emailService.acceptRequestMail(request);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
