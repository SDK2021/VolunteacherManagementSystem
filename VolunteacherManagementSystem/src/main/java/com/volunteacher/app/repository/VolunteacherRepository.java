package com.volunteachers.database.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.volunteachers.database.model.Volunteacher;

public interface VolunteacherRepository extends PagingAndSortingRepository<Volunteacher, Integer>{

}
