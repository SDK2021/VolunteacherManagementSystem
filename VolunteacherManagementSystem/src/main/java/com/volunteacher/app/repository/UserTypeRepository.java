package com.volunteachers.database.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.volunteachers.database.model.UserType;

public interface UserTypeRepository extends PagingAndSortingRepository<UserType, Integer>{
	
	public List<UserType> findByType(String type);
}
