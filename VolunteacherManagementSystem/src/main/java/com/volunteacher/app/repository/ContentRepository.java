package com.volunteachers.database.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.volunteachers.database.model.Content;

public interface ContentRepository extends PagingAndSortingRepository<Content, Integer>{

}
