package com.volunteacher.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Kid;

@Repository
public interface KidRepository extends PagingAndSortingRepository<Kid, Integer>{

}
