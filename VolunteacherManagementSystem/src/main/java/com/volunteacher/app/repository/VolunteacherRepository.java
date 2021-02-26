package com.volunteacher.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Volunteacher;

@Repository
public interface VolunteacherRepository extends PagingAndSortingRepository<Volunteacher, Integer>{

}
