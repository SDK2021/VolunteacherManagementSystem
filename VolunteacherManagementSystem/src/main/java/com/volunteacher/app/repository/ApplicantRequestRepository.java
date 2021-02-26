package com.volunteacher.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.ApplicantRequest;

@Repository
public interface ApplicantRequestRepository extends PagingAndSortingRepository<ApplicantRequest, Integer>{

}
