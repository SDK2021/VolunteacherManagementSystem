package com.volunteacher.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Project;

@Repository
public interface ProjectRepository extends PagingAndSortingRepository<Project, Integer>{
	
	@Query(value = "select COUNT(*) from project_users where users_user_id = :uid",nativeQuery = true)
	public int TotalProjectByUser(int uid);
	
}
