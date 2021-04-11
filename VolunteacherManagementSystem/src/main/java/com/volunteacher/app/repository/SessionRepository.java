package com.volunteacher.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Session;

@Repository
public interface SessionRepository extends PagingAndSortingRepository<Session, Long>{
	
}
