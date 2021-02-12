package com.volunteachers.database.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.volunteachers.database.model.ApplicantRequest;

public interface ApplicantRequestRepository extends PagingAndSortingRepository<ApplicantRequest, Integer>{

}
