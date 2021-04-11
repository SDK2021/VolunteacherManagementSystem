package com.volunteacher.app.service.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.Session;
import com.volunteacher.app.model.SessionReport;
import com.volunteacher.app.repository.SessionReportRepository;
import com.volunteacher.app.repository.SessionRepository;
import com.volunteacher.app.service.interfaces.SessionService;

@Service
public class SessionServiceImpl implements SessionService {
	
	@Autowired
	SessionRepository sessionRepository;
	
	@Autowired
	SessionReportRepository sessionReportRepository;
	
	@Override
	public ResponseEntity<Object> addSession(Session session)
	{
		try {
			Session saveSession = sessionRepository.save(session);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveSession);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creating Session");
		}
	}

	@Override
	public ResponseEntity<Object> sessionList() 
	{
		try {
			List<Session> sessionList = (List<Session>)sessionRepository.findAll(Sort.by("CreationDate").descending());
			
			if(sessionList.size() < 1)
				throw new ResourceNotFoundException("Session List Not found");
			return ResponseEntity.status(HttpStatus.OK).body(sessionList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Sessions");
		}
	}

	@Override
	public ResponseEntity<Object> sessionById(Long id)
	{
		try {
			Session session = sessionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Session not found for id: "+id));
			return ResponseEntity.status(HttpStatus.OK).body(session);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Session for id: "+ id);
		}
	}

	@Override
	public ResponseEntity<Object> updateSession(Session session, Long id) 
	{
		Session updateSession = sessionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Session not found for id: "+id));
		updateSession.setProject(session.getProject());
		updateSession.setEndingTime(session.getEndingTime());
		updateSession.setSessionDate(session.getSessionDate());
		updateSession.setStartingTime(session.getStartingTime());
		updateSession.setVillage(session.getVillage());
		
		try {
			sessionRepository.save(updateSession);
			return ResponseEntity.status(HttpStatus.OK).body(updateSession);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in updating Session for id:" +id);
		}
	}

	@Override
	public ResponseEntity<Object> deleteSession(Long id) 
	{
		sessionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Session not found for id: "+id));
		
		try {
			sessionRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Session deleted for id:" + id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in Deleting Session for id:" +id);
		}
	}

	@Override
	public ResponseEntity<Object> addSessionReport(SessionReport sessionReport) 
	{
		try {
			SessionReport saveSessionReport = sessionReportRepository.save(sessionReport);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveSessionReport);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creating Session Report");
		}
	}

	@Override
	public ResponseEntity<Object> sessionReportList() 
	{
		try {
			List<SessionReport> sessionReportList = (List<SessionReport>)sessionReportRepository.findAll();
			return ResponseEntity.status(HttpStatus.OK).body(sessionReportList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Session Report ");
		}
	}

	@Override
	public ResponseEntity<Object> sessionReport(int id) 
	{
		try {
			SessionReport sessionReport = sessionReportRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Session report not found for id: "+id));
			return ResponseEntity.status(HttpStatus.OK).body(sessionReport);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Session report for id: "+ id);
		}
	}

	@Override
	public ResponseEntity<Object> deleteSessionReport(int id) 
	{
		sessionReportRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Session report not found for id: "+id));
		
		try {
			sessionReportRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Session Report deleted for id:" + id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in Deleting Session Report for id:" +id);
		}
		
	}
}
