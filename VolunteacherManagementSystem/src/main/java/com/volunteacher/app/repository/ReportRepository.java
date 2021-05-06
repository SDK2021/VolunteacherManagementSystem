package com.volunteacher.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Report;

@Repository
public interface ReportRepository extends PagingAndSortingRepository<Report, Integer>{

}
