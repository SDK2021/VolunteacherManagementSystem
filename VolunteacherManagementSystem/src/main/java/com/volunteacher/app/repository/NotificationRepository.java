package com.volunteacher.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Notification;

@Repository
public interface NotificationRepository extends PagingAndSortingRepository<Notification, Integer>{

}

