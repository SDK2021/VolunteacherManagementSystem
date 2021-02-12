package com.volunteachers.database.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.volunteachers.database.model.School;

public interface SchoolRepository extends PagingAndSortingRepository<School, Integer>{

}
