package com.volunteacher.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Session;

@Repository
public interface SessionRepository extends PagingAndSortingRepository<Session, Long>{
	@Query(nativeQuery = true, value = "select * from session where MONTH(session_date) = :month and YEAR(session_date) = :year ORDER BY creation_date")
	public List<Session> sessionByMonthAndYear(int month, int year);
	
	@Query(nativeQuery = true, value = "select COUNT(*) from session_users where users_user_id=:userId")
	public int totalSessionByUser(int userId);
}
