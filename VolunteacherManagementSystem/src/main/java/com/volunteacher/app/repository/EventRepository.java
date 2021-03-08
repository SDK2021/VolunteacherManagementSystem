package com.volunteacher.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Event;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Integer> {

	@Query(value = "SELECT * FROM event WHERE MONTH(event_date) = MONTH(NOW())", nativeQuery = true )
	List<Event> findAllByEvent();
}
