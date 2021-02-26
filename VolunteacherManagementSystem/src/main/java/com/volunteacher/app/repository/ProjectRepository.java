package com.volunteacher.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Project;

@Repository
public interface ProjectRepository extends PagingAndSortingRepository<Project, Integer>{

}
