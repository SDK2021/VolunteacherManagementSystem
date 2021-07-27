package com.volunteacher.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.volunteacher.app.model.ApplicantRequest;
import com.volunteacher.app.model.Content;
import com.volunteacher.app.service.interfaces.ApplicantRequestService;
import com.volunteacher.app.service.interfaces.ContentService;

@RestController
@RequestMapping(path = "/vms")
public class AdminController {
	
	@Autowired
	ApplicantRequestService applicantRequestService;
	
	@Autowired
	ContentService contentService;
	
	@GetMapping("/applicant-requests")
	public ResponseEntity<Object> getApplicantRequestsList(@RequestParam("page") int page)
	{
		return applicantRequestService.requestList(page);
	}
	
	@GetMapping("/reject-requests")
	public ResponseEntity<Object> getAllRejectedRequestsList()
	{
		return applicantRequestService.allRejectedRequests();
	}
	
	@GetMapping("/accept-requests")
	public ResponseEntity<Object> getAllAcceptedRequestsList()
	{
		return applicantRequestService.allAcceptedRequests();
	}
	
	@PostMapping("/request-success/{id}")
	public ResponseEntity<Object> requestSuccess(@PathVariable int id)
	{
		return applicantRequestService.successRequest(id);
	}
	
	@PostMapping("/request-denied/{id}")
	public ResponseEntity<Object> requestDenied(@PathVariable int id)
	{
		return applicantRequestService.rejectRequest(id);
	}
	
	@PostMapping("/applicant-requests")
	public ResponseEntity<Object> addApplicantRequest(@RequestBody ApplicantRequest applicantRequest)
	{
		return applicantRequestService.addRequest(applicantRequest);
	}
	
	@GetMapping("/applicant-requests/{id}")
	public ResponseEntity<Object> getApplicantRequest(@PathVariable int id)
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
	
	@GetMapping("/contents/{id}")
	public ResponseEntity<Object> getContentByGroup(@PathVariable int id)
	{
		return contentService.contentByGroup(id);
	}
	
	@PutMapping("/contents/{id}")
	public ResponseEntity<Object> updateContent(@RequestBody Content content, @PathVariable int id)
	{
		return contentService.updateContent(content, id);
	}
	
	@DeleteMapping("/contents/{id}")
	public ResponseEntity<Object> deleteContent(@PathVariable int id)
	{
		return applicantRequestService.deleteRequest(id);
	}
	
	@GetMapping("/login")
	public ResponseEntity<String> login()
	{
		return ResponseEntity.status(HttpStatus.OK).body("Login page");
	}
}
