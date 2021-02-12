package com.volunteachers.database.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.volunteachers.database.model.Report;

public interface ReportRepository extends PagingAndSortingRepository<Report, Integer>{

}
