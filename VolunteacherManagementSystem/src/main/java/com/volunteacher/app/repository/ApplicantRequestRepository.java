package com.volunteacher.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	
	@Query(nativeQuery = true,value = "Select * from applicant_request where status = 2 ORDER BY request_date desc")
	List<ApplicantRequest> getAllRejectedRequests();
	
	@Query(nativeQuery = true,value = "Select * from applicant_request where status = 1 ORDER BY request_date desc")
	List<ApplicantRequest> getAllAcceptedRequests();
}
