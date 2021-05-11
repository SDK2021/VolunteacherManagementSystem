package com.volunteacher.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Announcement;

@Repository
public interface AnnouncementRepository extends PagingAndSortingRepository<Announcement, Long>{
	
	@Query(value = "select * from announcement where creation_date > DATE_SUB(NOW(),INTERVAL 7 DAY)", nativeQuery = true)
	Page<Announcement> findAllAnnouncements(Pageable pageable);
	
	
}
