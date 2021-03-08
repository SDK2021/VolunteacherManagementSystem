package com.volunteacher.app.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Taluka;

@Repository
public interface TalukaRepository extends PagingAndSortingRepository<Taluka, Integer>{
	
	public List<Taluka> findAllByDistrictDistrictId(int id);
}
