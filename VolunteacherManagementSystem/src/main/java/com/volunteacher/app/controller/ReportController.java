package com.volunteacher.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.volunteacher.app.model.Report;
import com.volunteacher.app.service.interfaces.ReportService;

@RestController
@RequestMapping(path = "/vms")
public class ReportController {
	
	@Autowired
	ReportService reportService;
	
	@PostMapping("/reports")
	public ResponseEntity<Object> addKid(@RequestBody Report report)
	{
		return reportService.addReport(report);
	}
	
	@GetMapping("/reports")
	public ResponseEntity<Object> getreportList(@RequestParam("year") int year)
	{
		return reportService.reportByCurrentYear(year);
	}
	
	@GetMapping("/reports/{id}")
	public ResponseEntity<Object> reportById(@PathVariable int id)
	{
		return reportService.reportById(id);
	}

	@PutMapping("/reports/{id}")
	public ResponseEntity<Object> updateReport(@RequestBody Report report, @PathVariable int id)
	{
		return reportService.updateReport(report, id);
	}
	
	@DeleteMapping("/reports/{id}")
	public ResponseEntity<Object> deleteReport(@PathVariable int id)
	{
		return reportService.deleteReport(id);
	}
}
