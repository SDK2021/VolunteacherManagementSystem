package com.volunteachers.database.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.volunteachers.database.model.Project;

public interface ProjectRepository extends PagingAndSortingRepository<Project, Integer>{

}
