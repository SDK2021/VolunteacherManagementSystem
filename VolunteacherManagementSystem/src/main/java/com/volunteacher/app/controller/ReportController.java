package com.volunteacher.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.volunteacher.app.model.Report;
import com.volunteacher.app.service.interfaces.ReportService;

@RestController
@RequestMapping(path = "/vms")
@CrossOrigin(origins="http://localhost:4200")
public class ReportController {
	
	@Autowired
	ReportService reportService;
	
	@PostMapping("/reports")
	public ResponseEntity<Object> addKid(@RequestBody Report report)
	{
		return reportService.addReport(report);
	}
	
	@GetMapping("/reports")
	public ResponseEntity<Object> getreportList()
	{
		return reportService.reportList();
	}
	
	@GetMapping("/reports/{id}")
	public ResponseEntity<Object> reportKid(@PathVariable int id)
	{
		return reportService.reportById(id);
	}

	@PutMapping("/reports/{id}")
	public ResponseEntity<Object> updateKid(@RequestBody Report report, @PathVariable int id)
	{
		return reportService.updateReport(report, id);
	}
	
	@DeleteMapping("/reports/{id}")
	public ResponseEntity<Object> deleteKid(@PathVariable int id)
	{
		return reportService.deleteReport(id);
	}
}
