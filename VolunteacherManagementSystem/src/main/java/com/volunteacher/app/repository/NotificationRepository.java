package com.volunteacher.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Notification;

@Repository
public interface NotificationRepository extends PagingAndSortingRepository<Notification, Long>{

	@Query(value = "select * from notification where user_type=:userType", nativeQuery = true)
	List<Notification> notificationByUser(String userType);
}

