package com.volunteacher.app.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Attendance;
import com.volunteacher.app.model.Kid;

public interface AttendanceService {
	
	public ResponseEntity<Object> attendanceList();
	
	public ResponseEntity<Object> addAttendance(Attendance attendance);
	
	public ResponseEntity<Object> attendanceByGroup(int groupId);
	
	public ResponseEntity<Object> attendanceByGroupAndSession(int groupId, Long sessionId);
	
	public ResponseEntity<Object> updateAttendance(Attendance attendance, Long id);
	
	public ResponseEntity<Object> attendanceBySession(Long sessionId);
	
	public ResponseEntity<Object> addKidsAttendance(Attendance attendance,String[] kidsIds);
	
	public List<Kid> createKidsList(String [] kidsIds);
}
