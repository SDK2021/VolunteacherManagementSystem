package com.volunteacher.app.service.classes;

import java.util.List;

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
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
		if(userService.userByPhoneNumber(request.getPhoneNumber()).getBody() != null || (this.requestByPhoneNumber(request.getPhoneNumber()).getBody() != null))
		{
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
	public ResponseEntity<Object> requestList(int page)
	{
		try {
			Pageable pageable = PageRequest.of(page, 7,Sort.by("request_date").descending());
			Page<ApplicantRequest> requestList = (Page<ApplicantRequest>) applicantRequestRepository.findAll(pageable);
			return ResponseEntity.status(HttpStatus.OK).body(requestList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Applicant request list");
		}
	}
	
	@Override
	public ResponseEntity<Object> allRejectedRequests()
	{
		try {
			List<ApplicantRequest> requestList = (List<ApplicantRequest>) applicantRequestRepository.getAllRejectedRequests();
			return ResponseEntity.status(HttpStatus.OK).body(requestList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Applicant request list");
		}
	}
	
	
	@Override
	public ResponseEntity<Object> allAcceptedRequests()
	{
		try {
			List<ApplicantRequest> requestList = (List<ApplicantRequest>) applicantRequestRepository.getAllAcceptedRequests();
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
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Applicant request by id");
		}
	}
	
	@Override
	public ResponseEntity<Object> deleteRequest(int id)
	{
		try {
			applicantRequestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Applicant Request Not found for Id: "+id));
			applicantRequestRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in Deleting Applicant Request for id:" +id);
		}
	}
	
	@Override
	public ResponseEntity<Object> requestByEmail(String email) {
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
		try {
			ApplicantRequest request = applicantRequestRepository.findByPhoneNumber(number);
			return ResponseEntity.status(HttpStatus.OK).body(request);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetching Request by Phonenumber");
		}
	}
	
	@Override
	public ResponseEntity<Object> successRequest(int requestId) {
		try {
			ApplicantRequest request = applicantRequestRepository.findById(requestId).orElseThrow(()->new ResourceNotFoundException("Error on sending zccepted mail"));;
			request.setStatus(1);
			applicantRequestRepository.save(request);
			emailService.acceptRequestMail(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on accept request");
		}
	}
	
	public ResponseEntity<Object> rejectRequest(int requestId)
	{
		try {
			ApplicantRequest request = applicantRequestRepository.findById(requestId).orElseThrow(()->new ResourceNotFoundException("Error on sending zccepted mail"));;
			request.setStatus(2);
			applicantRequestRepository.save(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on denied request");
		}
	}
}
