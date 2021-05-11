package com.volunteacher.app.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Session;

@Repository
public interface SessionRepository extends PagingAndSortingRepository<Session, Long>{
	@Query(nativeQuery = true, value = "select * from session where MONTH(session_date) = :month and YEAR(session_date) = :year ORDER BY session_date DESC")
	public Page<Session> sessionByMonthAndYear(int month, int year,Pageable pageable);
	
	@Query(nativeQuery = true, value = "select COUNT(*) from session_users where users_user_id=:userId")
	public int totalSessionByUser(int userId);
	
	@Query(value = "select COUNT(*) from session where project_project_id = :id",nativeQuery = true)
	public int totalSessionByProject(int id);
	
	@Query(value = "select COUNT(*) from session",nativeQuery = true)
	public int allSessions();
	
	@Query(value = "Select session_id from session where session_date > DATE_SUB(NOW(),INTERVAL 15 DAY)",nativeQuery = true)
	public List<Long> getPreviousSessions();
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM session_users WHERE sessions_session_id =:id",nativeQuery = true)
	public void deleteSessionsUsers(@Param("id") long id);
}
