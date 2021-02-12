package com.volunteachers.database.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.volunteachers.database.model.Kid;

public interface KidRepository extends PagingAndSortingRepository<Kid, Integer>{

}
