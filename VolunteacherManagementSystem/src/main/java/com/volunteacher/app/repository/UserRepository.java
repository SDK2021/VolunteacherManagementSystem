package com.volunteacher.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>{
	
	
	@Query(value = "SELECT * FROM user WHERE MONTH(dob) = MONTH(NOW()) AND DAY(dob) = DAY(NOW())" , nativeQuery = true)
	public List<User> findAllByDob();
	
	public User findByEmail(String email);
}
