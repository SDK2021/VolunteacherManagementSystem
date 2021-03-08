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
	public List<Attendance> attendanceList()
	{
		List<Attendance> attendanceList = (List<Attendance>) attendanceRepository.findAll();
		
		if(attendanceList.size() < 1)
			throw new ResourceNotFoundException("Attendance List not found");
		
		return  attendanceList;
	}
	
	@Override
	public ResponseEntity<Object> addAttendance(Attendance attendance)
	{
		try {
			Attendance saveattendance = attendanceRepository.save(attendance);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveattendance);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on creating Attendance");
		}
	}

	@Override
	public List<Attendance> attendanceByGroup(int groupId) 
	{
		List<Attendance> attendanceList = attendanceRepository.findAllByGroupGroupId(groupId);
		return attendanceList;
	}

	@Override
	public Attendance attendanceByGroupAndSession(int groupId, Long sessionId) 
	{
		Attendance attendance = attendanceRepository.findAllByGroupGroupIdAndSessionSessionId(groupId, sessionId);
		return attendance;
	}
	
	@Override
	public ResponseEntity<Object> updateAttendance(Attendance attendance, Long id)
	{
		Attendance updateAttendance = attendanceRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Attendance not found for id: "+id));
		
		updateAttendance.setKids(attendance.getKids());
		attendanceRepository.save(updateAttendance);
		return ResponseEntity.status(HttpStatus.OK).body(updateAttendance);
	}

	@Override
	public List<Attendance> attendanceBySession(Long sessionId) {
		List<Attendance> attendanceList = attendanceRepository.findAllBySessionSessionId(sessionId);
		return attendanceList;
	}
	
	
}
