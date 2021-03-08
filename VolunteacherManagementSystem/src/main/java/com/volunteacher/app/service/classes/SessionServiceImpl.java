package com.volunteacher.app.service.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public ResponseEntity<Object> addSession(Session session) {
		try {
			Session addsession = sessionRepository.save(session);
			return ResponseEntity.status(HttpStatus.CREATED).body(addsession);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creating Session");
		}
	}

	@Override
	public List<Session> sessionList() {
		
		List<Session> sessionList = (List<Session>)sessionRepository.findAll();
		
		if(sessionList.size() < 1)
			throw new ResourceNotFoundException("Session List Not found");
		
		return sessionList;
	}

	@Override
	public Session sessionById(Long id) {
		Session session = sessionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Session not found for id: "+id));
		return session;
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
		
		sessionRepository.save(updateSession);
		return ResponseEntity.status(HttpStatus.OK).body(updateSession);
	}

	@Override
	public ResponseEntity<Object> deleteSession(Long id) 
	{
		sessionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Session not found for id: "+id));
		sessionRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Session deleted for id:" + id);
	}

	@Override
	public ResponseEntity<Object> addSessionReport(SessionReport sessionReport) {
		
		try {
			SessionReport sessionreport = sessionReportRepository.save(sessionReport);
			return ResponseEntity.status(HttpStatus.CREATED).body(sessionreport);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creating Session Report");
		}
	}

	@Override
	public List<SessionReport> sessionReportList() {
		
		List<SessionReport> sessionReportList = (List<SessionReport>)sessionReportRepository.findAll();
		
		if(sessionReportList.size() < 1)
			throw new ResourceNotFoundException("Session report List Not found");
		
		return sessionReportList;
	}

	@Override
	public SessionReport sessionReport(int id) {
		SessionReport sessionReport = sessionReportRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Session report not found for id: "+id));
		return sessionReport;
	}

	@Override
	public ResponseEntity<Object> deleteSessionReport(int id) {
		sessionReportRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Session report not found for id: "+id));
		sessionReportRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Session Report deleted for id:" + id);
		
	}
}
