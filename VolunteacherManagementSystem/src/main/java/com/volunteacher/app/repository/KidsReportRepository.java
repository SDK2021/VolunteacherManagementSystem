package com.volunteacher.app.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.KidsReport;

@Repository
public interface KidsReportRepository extends PagingAndSortingRepository<KidsReport, Integer>{

	public List<KidsReport> findAllByKidKidId(Long id,Sort sort);
	
	@Query(nativeQuery = true, value = "select * from Kids_report where YEAR(created_date) = :year and kid_kid_id = :kidId ORDER BY (created_date) DESC")
	public List<KidsReport> KidsReportByYear(Long kidId, int year);
}
