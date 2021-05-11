package com.volunteacher.app.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Village;

@Repository
public interface VillageRepository extends PagingAndSortingRepository<Village, Integer>{	
	public List<Village> findAllByTalukaTalukaId(int id);
}
