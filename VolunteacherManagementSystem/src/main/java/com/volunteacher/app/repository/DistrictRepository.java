package com.volunteacher.app.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.District;

@Repository
public interface DistrictRepository extends PagingAndSortingRepository<District, Integer>{
	
	public List<District> findAllByStateStateId(int id);
}
