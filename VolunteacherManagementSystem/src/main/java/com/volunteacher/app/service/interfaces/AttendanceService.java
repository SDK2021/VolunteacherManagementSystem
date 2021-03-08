package com.volunteacher.app.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Attendance;

public interface AttendanceService {
	
	public List<Attendance> attendanceList();
	
	public ResponseEntity<Object> addAttendance(Attendance attendance);
	
	public List<Attendance> attendanceByGroup(int groupId);
	
	public Attendance attendanceByGroupAndSession(int groupId, Long sessionId);
	
	public ResponseEntity<Object> updateAttendance(Attendance attendance, Long id);
	
	public List<Attendance> attendanceBySession(Long sessionId);
}
