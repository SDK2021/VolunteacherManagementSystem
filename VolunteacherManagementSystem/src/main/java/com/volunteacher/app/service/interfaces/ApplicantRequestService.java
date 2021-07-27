package com.volunteacher.app.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.ApplicantRequest;

public interface ApplicantRequestService {
	
	public ResponseEntity<Object> addRequest(ApplicantRequest request);
	
	public ResponseEntity<Object> requestList(int page);
	
	public ResponseEntity<Object> requestById(int id);
	
	public ResponseEntity<Object> deleteRequest(int id);
	
	public ResponseEntity<Object> requestByPhoneNumber(String number);
	
	public ResponseEntity<Object> requestByEmail(String email);
	
	public ResponseEntity<Object> successRequest(int requestId);
	
	public ResponseEntity<Object> rejectRequest(int requestId);
	
	public ResponseEntity<Object> allRejectedRequests();
	
	public ResponseEntity<Object> allAcceptedRequests();
}
