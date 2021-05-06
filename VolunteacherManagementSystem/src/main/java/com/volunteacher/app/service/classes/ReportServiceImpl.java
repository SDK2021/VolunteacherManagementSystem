package com.volunteacher.app.service.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.Report;
import com.volunteacher.app.repository.ReportRepository;
import com.volunteacher.app.service.interfaces.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	ReportRepository reportRepository;
		
	@Override
	public ResponseEntity<Object> addReport(Report Report) 
	{
		try {
			Report saveReport = reportRepository.save(Report);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveReport);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on Report Creation");
		}
	}

	@Override
	public ResponseEntity<Object> reportList() 
	{
		try {
			List<Report> reportList = (List<Report>)reportRepository.findAll();
			return ResponseEntity.status(HttpStatus.OK).body(reportList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Reports");
		}
	}

	@Override
	public ResponseEntity<Object> updateReport(Report report, int id) {
		
		Report updateReport = reportRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Report is not found for id: "+id));
		
		updateReport.setTitle(report.getTitle());
		updateReport.setDescription(report.getDescription());
	
		try {
			reportRepository.save(updateReport);
			return ResponseEntity.status(HttpStatus.OK).body(updateReport);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in updating Report for id:" +id);
		}
	}

	@Override
	public ResponseEntity<Object> reportById(int id) 
	{
		try {
			Report report = reportRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Report is not found for id: "+id));
			return ResponseEntity.status(HttpStatus.OK).body(report);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Report for id: "+ id);
		}
	}

	@Override
	public ResponseEntity<Object> deleteReport(int id) 
	{
		reportRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Report is not found for id: "+id));
		
		try {
			reportRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in Deleting Report for id:" +id);
		}
	}

}
