package com.volunteacher.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Event;
import com.volunteacher.app.model.Session;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Integer> {

	@Query(value = "SELECT * FROM event WHERE MONTH(event_date) = MONTH(NOW()) and YEAR(event_date) = YEAR(NOW()) and DATE(event_date) > DATE(NOW())", nativeQuery = true )
	Page<Event> eventByMonthAndYear(Pageable pageable);
	
	@Query(value = "select * from event where MONTH(event_date) = :month and YEAR(event_date) = :year ORDER BY event_date DESC", nativeQuery = true )
	Page<Event> eventsByTime(Pageable pageable,int month, int year);
	
	@Query(value = "select COUNT(*) from event",nativeQuery = true)
	int allEvents();
	
	Event findByEventId(int eventId);
	
	public Page<Event> findAllByProjectProjectId(Pageable pageable, int pid);
	
	public Page<Event> findAllByVillageVillageId(Pageable pageable, int vid);
}
