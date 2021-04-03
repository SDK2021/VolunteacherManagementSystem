package com.volunteacher.app.service.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.Attendance;
import com.volunteacher.app.repository.AttendanceRepository;
import com.volunteacher.app.service.interfaces.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService{
	
	@Autowired
	AttendanceRepository attendanceRepository;
	
	@Override
	public ResponseEntity<Object> attendanceList()
	{
		try {
			List<Attendance> attendanceList = (List<Attendance>) attendanceRepository.findAll();
			return ResponseEntity.status(HttpStatus.OK).body(attendanceList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch attendance list");
		}
	}
	
	@Override
	public ResponseEntity<Object> addAttendance(Attendance attendance)
	{
		try {
			Attendance saveAttendance = attendanceRepository.save(attendance);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveAttendance);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creating Attendance");
		}
	}

	@Override
	public ResponseEntity<Object> attendanceByGroup(int groupId) 
	{
		try {
			List<Attendance> attendanceList = attendanceRepository.findAllByGroupGroupId(groupId);
			return ResponseEntity.status(HttpStatus.OK).body(attendanceList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch attendance list");
		}
	}

	@Override
	public ResponseEntity<Object> attendanceByGroupAndSession(int groupId, Long sessionId) 
	{
		try {
			Attendance attendance = attendanceRepository.findAllByGroupGroupIdAndSessionSessionId(groupId, sessionId);
			return ResponseEntity.status(HttpStatus.OK).body(attendance);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch attendance");
		}
	}
	
	@Override
	public ResponseEntity<Object> updateAttendance(Attendance attendance, Long id)
	{
		Attendance updateAttendance = attendanceRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Attendance not found for id: "+id));
		
		updateAttendance.setKids(attendance.getKids());
		try {
			attendanceRepository.save(updateAttendance);
			return ResponseEntity.status(HttpStatus.OK).body(updateAttendance);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in updating Attendance for id:" +id);
		}
	}

	@Override
	public ResponseEntity<Object> attendanceBySession(Long sessionId) 
	{

		try {
			List<Attendance> attendanceList = attendanceRepository.findAllBySessionSessionId(sessionId);
			return ResponseEntity.status(HttpStatus.OK).body(attendanceList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch attendance list");
		}
	}
}
