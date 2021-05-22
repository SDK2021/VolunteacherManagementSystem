package com.volunteacher.app.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Project;

@Repository
public interface ProjectRepository extends PagingAndSortingRepository<Project, Integer>{
	
	@Query(value = "select COUNT(*) from project_users where users_user_id = :userId",nativeQuery = true)
	public int TotalProjectByUser(int userId);
	
	@Query(value = "select projects_project_id from project_users where users_user_id = :userId", nativeQuery = true)
	public List<Integer> projectNumberByUser(int userId);
	
	@Query(value = "select COUNT(*) from session where project_project_id=:projectId",nativeQuery = true)
	public int TotalSessionByProject(int projectId);
	
	@Query(value = "select COUNT(*) from project_kids where projects_project_id=:projectId",nativeQuery = true)
	public int TotalKidsByProject(int projectId);
	
	@Query(value = "select COUNT(*) from project_users where projects_project_id=:projectId",nativeQuery = true)
	public int TotalVolunteachersByProject(int projectId);
	
	@Query(value = "Select COUNT(*) from event where project_project_id =:projectId",nativeQuery = true)
	int totalEventByProject(int projectId);
	

	@Transactional
	@Modifying
	@Query(value="DELETE FROM project_users WHERE projects_project_id =:id",nativeQuery = true)
	public void deleteProjectsUsers(@Param("id") long id);
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM project_kids WHERE projects_project_id =:id",nativeQuery = true)
	public void deleteProjectsKids(@Param("id") long id);
	
	public Project findByProjectName(String name);
	
}
