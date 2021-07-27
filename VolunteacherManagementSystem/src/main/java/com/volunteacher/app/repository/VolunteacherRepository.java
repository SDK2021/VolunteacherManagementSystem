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

import com.volunteacher.app.model.Volunteacher;

@Repository
public interface VolunteacherRepository extends PagingAndSortingRepository<Volunteacher, Integer>{
	
	@Query(value = "SELECT * FROM volunteacher WHERE MONTH(joining_date) = MONTH(NOW()) AND DAY(joining_date) = DAY(NOW()) AND YEAR(joining_date) = YEAR(NOW()) ", nativeQuery = true )
	public List<Volunteacher> findAllByDay();
	
	public Volunteacher findByUserUserId(long id); 
	
	@Query(value = "select COUNT(*) from volunteacher",nativeQuery = true)
	public int allVolunteacher();
	
	@Query(value = "select * from volunteacher where DATE(NOW()) < DATE_ADD(joining_date,INTERVAL 1 YEAR)  ORDER BY joining_date DESC",nativeQuery = true)
	public Page<Volunteacher> newVolunteachers(Pageable pageable);
	
	@Query(value ="select * from volunteacher where DATE(NOW()) < DATE_ADD(joining_date,INTERVAL 1 YEAR) ORDER BY joining_date DESC",nativeQuery = true)
	public List<Volunteacher> newAllVolunteachers();
	
	public Page<Volunteacher> findAllByStatus(Pageable pageable,int id);
	
	public Page<Volunteacher> findAllByVillageVillageId(Pageable pageable,int id);
	
	public Page<Volunteacher> findAllByUserTypeTypeId(Pageable pageable,int type);
	
	@Query(value = "select * from volunteacher where user_user_id in (select users_user_id from project_users where projects_project_id = :project)",nativeQuery = true)
	public Page<Volunteacher> findByProject(Pageable pageable, int project);
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM project_users WHERE users_user_id =:id",nativeQuery = true)
	public void deleteVolunteacherProjects(@Param("id") long id);
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM session_users WHERE users_user_id =:id",nativeQuery = true)
	public void deleteVolunteacherSessions(@Param("id") long id);
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM event_users WHERE users_user_id =:id",nativeQuery = true)
	public void deleteVolunteacherEvents(@Param("id") long id);
}
