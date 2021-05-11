package com.volunteacher.app.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.volunteacher.app.model.Attendance;

public interface AttendanceRepository extends PagingAndSortingRepository<Attendance, Long> {
	
	public List<Attendance> findAllByGroupGroupId(int groupid);
	
	public Attendance findAllByGroupGroupIdAndSessionSessionId(int groupId,Long sessionId);
	
	public List<Attendance> findAllBySessionSessionId(Long groupid);
}
