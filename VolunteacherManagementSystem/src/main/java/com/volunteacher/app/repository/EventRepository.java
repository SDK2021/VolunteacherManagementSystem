package com.volunteacher.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Event;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Integer> {

}
