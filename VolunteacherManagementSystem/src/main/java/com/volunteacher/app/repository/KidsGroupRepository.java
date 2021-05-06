package com.volunteacher.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.KidsGroup;

@Repository
public interface KidsGroupRepository extends PagingAndSortingRepository<KidsGroup, Integer>{

	KidsGroup findByGroupName(String name);
}
