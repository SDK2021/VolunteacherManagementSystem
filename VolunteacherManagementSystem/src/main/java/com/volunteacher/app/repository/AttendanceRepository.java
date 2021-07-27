package com.volunteacher.app.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.volunteacher.app.model.Attendance;

public interface AttendanceRepository extends PagingAndSortingRepository<Attendance, Long> {
	
	public List<Attendance> findAllByKidsGroupGroupId(int groupid);
	
	public List<Attendance> findAllByKidsGroupGroupIdAndSessionSessionId(int groupId,Long sessionId);
	
	
	public List<Attendance> findAllBySessionSessionId(Long groupid);
}
