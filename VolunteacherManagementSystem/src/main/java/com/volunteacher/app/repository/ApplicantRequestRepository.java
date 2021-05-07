package com.volunteacher.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.ApplicantRequest;

@Repository
public interface ApplicantRequestRepository extends PagingAndSortingRepository<ApplicantRequest, Integer>{

	public ApplicantRequest findByEmailId(String email);
	
	public ApplicantRequest findByPhoneNumber(String number);
	
	@Query(nativeQuery = true,value = "Select * from applicant_request where status =0")
	Page<ApplicantRequest> findAll(Pageable pageable);
}
