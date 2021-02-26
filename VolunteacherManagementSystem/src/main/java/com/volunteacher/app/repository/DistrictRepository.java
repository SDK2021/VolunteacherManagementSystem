package com.volunteacher.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.District;

@Repository
public interface DistrictRepository extends PagingAndSortingRepository<District, Integer>{

}
