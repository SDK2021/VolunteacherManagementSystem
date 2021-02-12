package com.volunteachers.database.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.volunteachers.database.model.Event;

public interface EventRepository extends PagingAndSortingRepository<Event, Integer> {

}
