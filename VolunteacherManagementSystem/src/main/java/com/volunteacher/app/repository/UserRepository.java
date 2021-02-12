package com.volunteachers.database.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.volunteachers.database.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer>{

}
