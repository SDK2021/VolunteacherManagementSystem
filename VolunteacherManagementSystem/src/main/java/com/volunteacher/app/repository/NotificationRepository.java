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
	
	@Query(value = "select * from notification INNER JOIN session ON notification.session_session_id = session.session_id WHERE MONTH(session.session_date) = :month and YEAR(session.session_date)= :year and user_type=:userType ORDER BY session.session_date DESC", nativeQuery = true)
	List<Notification> notificationByMonthAndYear(int month, int year,String userType);
}

