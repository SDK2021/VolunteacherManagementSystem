package com.volunteacher.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer>{

}
