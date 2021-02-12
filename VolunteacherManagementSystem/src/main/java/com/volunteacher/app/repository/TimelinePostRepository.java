package com.volunteachers.database.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.volunteachers.database.model.TimelinePost;

public interface TimelinePostRepository extends PagingAndSortingRepository<TimelinePost, Integer>{

}
