package com.volunteacher.app.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.SessionReport;

@Repository
public interface SessionReportRepository extends PagingAndSortingRepository<SessionReport, Integer>{

	public List<SessionReport> findAllBySessionSessionId(long id);
	
}
