package com.volunteacher.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Report;

@Repository
public interface ReportRepository extends PagingAndSortingRepository<Report, Integer>{

	@Query(value = "select * from report where YEAR(creation_date) = YEAR(NOW())",nativeQuery = true)
	List<Report> reportByYear();
}
