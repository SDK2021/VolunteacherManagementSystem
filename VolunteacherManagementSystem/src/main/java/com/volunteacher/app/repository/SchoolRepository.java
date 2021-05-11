package com.volunteacher.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.School;

@Repository
public interface SchoolRepository extends PagingAndSortingRepository<School, Integer>{

}
