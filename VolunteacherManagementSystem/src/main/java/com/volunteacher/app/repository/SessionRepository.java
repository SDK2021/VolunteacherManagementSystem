package com.volunteachers.database.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.volunteachers.database.model.Session;

public interface SessionRepository extends PagingAndSortingRepository<Session, Integer>{

}
