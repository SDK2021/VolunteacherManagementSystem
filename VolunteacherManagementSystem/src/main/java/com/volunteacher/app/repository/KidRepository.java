package com.volunteacher.app.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Kid;


@Repository
public interface KidRepository extends PagingAndSortingRepository<Kid, Long>{

	public List<Kid> findAllByGroupGroupId(int group);
	
	public List<Kid> findAllByVillageVillageIdAndGroupGroupId(int vid, int gid);
}
