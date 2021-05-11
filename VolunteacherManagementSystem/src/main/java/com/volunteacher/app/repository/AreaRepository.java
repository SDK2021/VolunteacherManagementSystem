package com.volunteacher.app.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.volunteacher.app.model.Area;

public interface AreaRepository extends PagingAndSortingRepository<Area, Integer> {
	List<Area> findAllByVillageVillageId(int id);
}
