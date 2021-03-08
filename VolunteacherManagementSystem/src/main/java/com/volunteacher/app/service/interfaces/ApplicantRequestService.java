package com.volunteacher.app.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.ApplicantRequest;

public interface ApplicantRequestService {
	
	public ResponseEntity<Object> addRequest(ApplicantRequest request);
	
	public List<ApplicantRequest> requestList();
	
	public ApplicantRequest requestById(int id);
	
	public ResponseEntity<Object> deleteRequest(int id);
}
