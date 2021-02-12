package com.volunteachers.database.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.volunteachers.database.model.Notification;

public interface NotificationRepository extends PagingAndSortingRepository<Notification, Integer>{

}

