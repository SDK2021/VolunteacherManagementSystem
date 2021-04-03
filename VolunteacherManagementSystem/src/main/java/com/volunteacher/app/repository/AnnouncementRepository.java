package com.volunteacher.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Announcement;

@Repository
public interface AnnouncementRepository extends PagingAndSortingRepository<Announcement, Long>{

}
