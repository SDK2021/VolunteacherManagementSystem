package com.volunteachers.database.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.volunteachers.database.model.Requirement;

public interface RequirementRepository extends PagingAndSortingRepository<Requirement, Integer>{

}
