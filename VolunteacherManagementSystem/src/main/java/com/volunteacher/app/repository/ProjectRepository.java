package com.volunteacher.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
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
	
	public Project findByProjectName(String name);
	
}
