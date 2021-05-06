package com.volunteacher.app.service.classes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.Session;
import com.volunteacher.app.model.SessionReport;
import com.volunteacher.app.model.User;
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
	public ResponseEntity<Object> sessionList(int month,int year) 
	{
		try {
			
			List<Session> sessionList = (List<Session>)sessionRepository.sessionByMonthAndYear(month, year);
			
//			if(sessionList.size() < 1)
//				throw new ResourceNotFoundException("Session List Not found");
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
			sessionRepository.deleteSessionsUsers(id);
			sessionRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
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
	public ResponseEntity<Object> sessionReportsBySession(long id) {
		try {
			List<SessionReport> sessionReports = sessionReportRepository.findAllBySessionSessionId(id);
			return ResponseEntity.status(HttpStatus.OK).body(sessionReports);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Session report by session id: "+ id);
		}
	}

	@Override
	public ResponseEntity<Object> deleteSessionReport(int id) 
	{
		sessionReportRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Session report not found for id: "+id));
		
		try {
			sessionReportRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in Deleting Session Report for id:" +id);
		}
		
	}

	@Override
	public ResponseEntity<Object> totalSessionsByUser(int userId) 
	{
		try {
			int totalSession = sessionRepository.totalSessionByUser(userId);
			return ResponseEntity.status(HttpStatus.OK).body(totalSession);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in fetching Session number for id:" +userId);
		}
	}

	@Override
	public ResponseEntity<Object> addSessionVolunteachers(List<User> users,String sessionId) {
		Session session = sessionRepository.findById(Long.parseLong(sessionId)).orElseThrow(()->new ResourceNotFoundException("Error on fetching session by id in atteding session"));
		try {
			session.setUsers(users);
			sessionRepository.save(session);
			return ResponseEntity.status(HttpStatus.OK).body(session);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in add volunteacher for session:");
		}
	}

	@Override
	public ResponseEntity<Object> allSessionList() {
		try {
			
			List<Session> sessionList = (List<Session>)sessionRepository.findAll();
			return ResponseEntity.status(HttpStatus.OK).body(sessionList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Sessions");
		}
	}
	
	@Override
	public ResponseEntity<Object> getTotalSessions() 
	{
		try 
		{
			return ResponseEntity.status(HttpStatus.OK).body(sessionRepository.allSessions());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetching total Sessions");
		}
	}

	@Override
	public ResponseEntity<Object> getSessionsRequirements() {
		List<SessionReport> sessionReports = new ArrayList<>();
		
		try {
			List<Long> sessions = sessionRepository.getPreviousSessions();
			for (Long sessionId : sessions) {
			//	System.out.println(sessions.size());
				List<SessionReport> reportList = sessionReportRepository.findAllBySessionSessionId(sessionId);
				System.out.println(sessionId);
				for (SessionReport report : reportList) {
					if(report.getRequirements() != null)
					{
						sessionReports.add(report);
					}
				}
			}
			return ResponseEntity.status(HttpStatus.OK).body(sessionReports);
 		} catch (Exception e) {
 			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetching Requirements");
		}
	}
	
	
}
