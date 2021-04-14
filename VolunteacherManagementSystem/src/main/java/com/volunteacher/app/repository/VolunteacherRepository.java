package com.volunteacher.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Volunteacher;

@Repository
public interface VolunteacherRepository extends PagingAndSortingRepository<Volunteacher, Integer>{
	
	@Query(value = "SELECT * FROM volunteacher WHERE MONTH(joining_date) = MONTH(NOW()) AND DAY(joining_date) = DAY(NOW()) AND YEAR(joining_date) = YEAR(NOW()) ", nativeQuery = true )
	public ResponseEntity<Object> findAllByDay();
	
	public Volunteacher findByUserUserId(long id); 
}
