package com.volunteacher.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Activity;

@Repository
public interface ActivityRepository extends PagingAndSortingRepository<Activity, Integer>{

}
