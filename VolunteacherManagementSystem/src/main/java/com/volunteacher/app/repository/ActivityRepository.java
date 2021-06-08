package com.volunteacher.app.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Activity;

@Repository
public interface ActivityRepository extends PagingAndSortingRepository<Activity, Integer>{

	@Transactional
	@Modifying
	@Query(value="DELETE FROM event_activities WHERE activities_activity_id =:id",nativeQuery = true)
	public void deleteActivityEvent(@Param("id") int id);
}
