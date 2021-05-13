package com.volunteacher.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Event;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Integer> {

	@Query(value = "SELECT * FROM event WHERE MONTH(event_date) = MONTH(NOW()) and YEAR(event_date) = YEAR(NOW()) and DATE(event_date) > DATE(NOW())", nativeQuery = true )
	Page<Event> eventByMonthAndYear(Pageable pageable);
	
	@Query(value = "select COUNT(*) from event",nativeQuery = true)
	int allEvents();
	
	Event findByEventId(int eventId);
}
