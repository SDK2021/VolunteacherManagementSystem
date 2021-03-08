package com.volunteacher.app.service.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public ResponseEntity<Object> addKidReport(KidsReport kidsReport) {
		
		try {
			KidsReport addReport = kidsReportRepository.save(kidsReport);
			return ResponseEntity.status(HttpStatus.CREATED).body(addReport);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creation kidsReport");
		}
	
	}

	@Override
	public List<KidsReport> kidReportList()
	{
		List<KidsReport> kidReportList = (List<KidsReport>) kidsReportRepository.findAll();
		
		if(kidReportList.size() < 1)
			throw new ResourceNotFoundException("Kids report not found");
		
		return kidReportList;
	}
	
	@Override
	public KidsReport KidReport(int id) 
	{
		KidsReport kidsReport = kidsReportRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Kids Report not found"));
		return kidsReport;
	}
	
	@Override
	public KidsReport kidReportByKid(int id)
	{
		KidsReport kidReport = kidsReportRepository.findByKidKidId(id);
		if(kidReport == null)
			throw new ResourceNotFoundException("Kid's report is not found for kid id: "+ id);
		return kidReport;
	}

	@Override
	public ResponseEntity<Object> updateKidReport(KidsReport kidReport, int id) {
		KidsReport updateKidReport = kidsReportRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Kid report not found for id: "+ id));
		updateKidReport.setAbhivyakti(kidReport.getAbhivyakti());
		updateKidReport.setArtCraft(kidReport.getArtCraft());
		updateKidReport.setAttendance(kidReport.getAttendance());
		updateKidReport.setDiscipline(kidReport.getDiscipline());
		updateKidReport.setEnglish(kidReport.getEnglish());
		updateKidReport.setGames(kidReport.getGames());
		updateKidReport.setGoshthi(kidReport.getGames());
		updateKidReport.setGoshthi(kidReport.getGoshthi());
		updateKidReport.setGujarati(kidReport.getGujarati());
		updateKidReport.setInteresArea(kidReport.getInteresArea());
		updateKidReport.setLiterature(kidReport.getLiterature());
		updateKidReport.setMaths(kidReport.getMaths());
		updateKidReport.setNationConnection(kidReport.getNationConnection());
		updateKidReport.setPrayer(kidReport.getPrayer());
		updateKidReport.setRemarks(kidReport.getRemarks());
		updateKidReport.setSports(kidReport.getSports());
		updateKidReport.setVolunteaching(kidReport.getVolunteaching());
		
		kidsReportRepository.save(updateKidReport);
		return ResponseEntity.status(HttpStatus.OK).body(updateKidReport);
		
	}
	
	@Override
	public ResponseEntity<Object> deleteKidReport(int id) 
	{	
		kidsReportRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("KidReport Not found for id: "+ id));
		kidsReportRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Kid report deleted for id: " + id);
	}
	
}
