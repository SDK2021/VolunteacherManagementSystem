package com.volunteacher.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Requirement;

@Repository
public interface RequirementRepository extends PagingAndSortingRepository<Requirement, Integer>{

}
