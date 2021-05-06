package com.volunteacher.app.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.TimelinePost;

@Repository
public interface TimelinePostRepository extends PagingAndSortingRepository<TimelinePost, Long>{
	
	@Query(value = "select COUNT(*) from timeline_post where created_by_user_id = :id",nativeQuery = true)
	int TotalPostByUser(int id);
	
	List<TimelinePost> findAllByCreatedByUserId(Long id,Sort sort);
}
