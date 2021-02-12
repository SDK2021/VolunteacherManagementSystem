package com.volunteachers.database.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.volunteachers.database.model.Activity;

public interface ActivityRepository extends PagingAndSortingRepository<Activity, Integer>{

}
