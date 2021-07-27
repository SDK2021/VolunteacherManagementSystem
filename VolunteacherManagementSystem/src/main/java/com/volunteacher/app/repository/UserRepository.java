package com.volunteacher.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>{
	
	
	@Query(value = "SELECT * FROM user WHERE MONTH(dob) = MONTH(NOW()) AND DAY(dob) = DAY(NOW())" , nativeQuery = true)
	public List<User> findAllByDob();
	
	public User findByEmail(String email);

	public User findByPhoneNumber(String number);
	
	@Query(value = "select * from user where user_id=:userId",nativeQuery = true)
	public User findByUserId(Long userId);

	public List<User> findAllByTypeTypeId(int id);
	
	@Query(value = "select * from user where user_type=:id",nativeQuery = true)
	public Page<User> findAllByType(Pageable pageable,int id);
	
	@Query(value = "SELECT * FROM user where user_id in (SELECT distinct users_user_id FROM session_users where sessions_session_id in (SELECT session_id FROM session where village_village_id=:villageId ));",nativeQuery = true)
	List<User> totalVolunteacherBySessionVillage(int villageId);
}
