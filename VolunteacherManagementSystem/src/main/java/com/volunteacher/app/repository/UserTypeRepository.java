package com.volunteacher.app.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.UserType;

@Repository
public interface UserTypeRepository extends PagingAndSortingRepository<UserType, Integer>{
	
	public List<UserType> findByType(String type);
}
