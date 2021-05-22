package com.volunteacher.app.service.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.KidsReport;
import com.volunteacher.app.repository.KidsReportRepository;
import com.volunteacher.app.service.interfaces.KidsReportService;

@Service
public class KidsReportServiceImpl implements KidsReportService{
	
	@Autowired
	KidsReportRepository kidsReportRepository;
	
	@Override
	public ResponseEntity<Object> addKidReport(KidsReport kidsReport) 
	{
		try {
			KidsReport saveKidsReport = kidsReportRepository.save(kidsReport);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveKidsReport);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creation kidsReport");
		}
	}

	@Override
	public ResponseEntity<Object> kidReportList()
	{
		try {
			List<KidsReport> kidReportList = (List<KidsReport>) kidsReportRepository.findAll();
			return ResponseEntity.status(HttpStatus.OK).body(kidReportList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Kids report");
		}
	}
	
	@Override
	public ResponseEntity<Object> kidReportById(int id) 
	{
		try {
			KidsReport kidsReport = kidsReportRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Kids Report not found"));
			return ResponseEntity.status(HttpStatus.OK).body(kidsReport);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Kids report");
		}
	}
	
	@Override
	public ResponseEntity<Object> kidReportByKid(Long id)
	{
		try {
			 List<KidsReport>  kidsReportList = kidsReportRepository.findAllByKidKidId(id,Sort.by("createdDate").descending());
			return ResponseEntity.status(HttpStatus.OK).body(kidsReportList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Kids report");
		}
	}
	
	@Override
	public ResponseEntity<Object> getLatestKidReport(long kidId)
	{
		try {
			 List<KidsReport>  kidsReportList = kidsReportRepository.findAllByKidKidId(kidId,Sort.by("createdDate").descending());
			return ResponseEntity.status(HttpStatus.OK).body(kidsReportList.get(0));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Kids report");
		}
	}

	@Override
	public ResponseEntity<Object> updateKidReport(KidsReport kidReport, int id) {	
		try {
			KidsReport updateKidReport = kidsReportRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Kid report not found for id: "+ id));
			
			updateKidReport.setAbhivyakti(kidReport.getAbhivyakti());
			updateKidReport.setArtCraft(kidReport.getArtCraft());
//			updateKidReport.setAttendance(kidReport.getAttendance());
			updateKidReport.setDiscipline(kidReport.getDiscipline());
			updateKidReport.setEnglish(kidReport.getEnglish());
			updateKidReport.setGames(kidReport.getGames());
			updateKidReport.setGoshthi(kidReport.getGames());
			updateKidReport.setGoshthi(kidReport.getGoshthi());
			updateKidReport.setGujarati(kidReport.getGujarati());
			updateKidReport.setInterestArea(kidReport.getInterestArea());
			updateKidReport.setLiterature(kidReport.getLiterature());
			updateKidReport.setMaths(kidReport.getMaths());
			updateKidReport.setPrayer(kidReport.getPrayer());
			updateKidReport.setRemarks(kidReport.getRemarks());
			updateKidReport.setSports(kidReport.getSports());
			updateKidReport.setVolunteaching(kidReport.getVolunteaching());
			kidsReportRepository.save(updateKidReport);
			return ResponseEntity.status(HttpStatus.OK).body(updateKidReport);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in updating Kids Report for id:" +id);
		}
		
	}
	
	@Override
	public ResponseEntity<Object> deleteKidReport(int id) 
	{	
		try {
			kidsReportRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("KidReport Not found for id: "+ id));
			kidsReportRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in Deleting Kids Report for id:" +id);
		}
	}

	@Override
	public ResponseEntity<Object> kidReportByYear(Long kid, int year) 
	{
		List<KidsReport> kidsReportList = kidsReportRepository.KidsReportByYear(kid, year);
		return ResponseEntity.status(HttpStatus.OK).body(kidsReportList);
	}
	
}
