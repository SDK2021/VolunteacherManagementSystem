package com.volunteacher.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.volunteacher.app.model.ApplicantRequest;
import com.volunteacher.app.model.Content;
import com.volunteacher.app.service.interfaces.ApplicantRequestService;
import com.volunteacher.app.service.interfaces.ContentService;

@RestController
@RequestMapping(path = "/vms")
@CrossOrigin(origins="http://localhost:4200")  
public class AdminController {
	
	@Autowired
	ApplicantRequestService applicantRequestService;
	
	@Autowired
	ContentService contentService;
	
	@GetMapping("/applicant-requests")
	public ResponseEntity<Object> getApplicantRequestsList()
	{
		return applicantRequestService.requestList();
	}
	
	@PostMapping("/applicant-requests")
	public ResponseEntity<Object> addApplicantRequest(@RequestBody ApplicantRequest applicantRequest)
	{
		return applicantRequestService.addRequest(applicantRequest);
	}
	
	@GetMapping("/applicant-requests/{id}")
	public ResponseEntity<Object> getApplicantRequests(@PathVariable int id)
	{
		return applicantRequestService.requestById(id);
	}
	
	@DeleteMapping("/applicant-requests/{id}")
	public ResponseEntity<Object> deleteApplicantRequest(@PathVariable int id)
	{
		return applicantRequestService.deleteRequest(id);
	}
	

	@GetMapping("/contents")
	public ResponseEntity<Object> getContentList()
	{
		return contentService.contentList();
	}
	
	@PostMapping("/contents")
	public ResponseEntity<Object> addContent(@RequestBody Content content) 
	{
		return contentService.addContent(content);
	}
}
