package com.volunteacher.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.volunteacher.app.service.interfaces.ExcelService;

@RestController
@RequestMapping(path = "/vms")
public class ExcelController {
	
	@Autowired
	ExcelService excelService;
	
	@GetMapping("/kids-download")
	public ResponseEntity<Object> kidsDownload()
	{
		return excelService.downloadKids();
	}
	
	@GetMapping("/volunteachers-download")
	public ResponseEntity<Object> volunteachersDownload()
	{
		return excelService.downloadVolunteachers();
	}
	
	@GetMapping("/events-download")
	public ResponseEntity<Object> eventsDownload()
	{
		return excelService.downloadEvents();
	}
	
	@GetMapping("/schools-download")
	public ResponseEntity<Object> schoolsDownload()
	{
		return excelService.downloadSchools();
	}
	
	@GetMapping("/sessions-download")
	public ResponseEntity<Object> sessionsDownload()
	{
		return excelService.downloadSessions();
	}
}
