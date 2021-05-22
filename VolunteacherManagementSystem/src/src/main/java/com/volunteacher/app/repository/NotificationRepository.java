package com.volunteacher.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Notification;

@Repository
public interface NotificationRepository extends PagingAndSortingRepository<Notification, Long>{

	@Query(value = "select * from notification where user_type=:userType ORDER BY created_date DESC", nativeQuery = true)
	Page<Notification> notificationByUser(String userType,Pageable pageable);
	
	@Query(value = "select * from notification INNER JOIN session ON notification.session_session_id = session.session_id WHERE MONTH(session.session_date) = :month and YEAR(session.session_date)= :year and user_type=:userType ORDER BY session.session_date DESC", nativeQuery = true)
	Page<Notification> notificationByMonthAndYear(int month, int year,String userType,Pageable pageable);
	
	@Query(value = "delete from notification where created_by_user_id = :id", nativeQuery = true)
	public long deleteByCreatedByUserId(long id);
}

