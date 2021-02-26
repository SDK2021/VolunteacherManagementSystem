package com.volunteacher.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.KidsReport;

@Repository
public interface KidsReportRepository extends PagingAndSortingRepository<KidsReport, Integer>{
	
}
