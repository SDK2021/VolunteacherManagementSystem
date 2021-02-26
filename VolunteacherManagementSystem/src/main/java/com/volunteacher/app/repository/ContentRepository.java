package com.volunteacher.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Content;

@Repository
public interface ContentRepository extends PagingAndSortingRepository<Content, Integer>{

}
