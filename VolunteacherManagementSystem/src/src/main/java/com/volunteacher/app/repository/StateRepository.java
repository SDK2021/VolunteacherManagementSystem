package com.volunteacher.app.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.State;

@Repository
public interface StateRepository extends PagingAndSortingRepository<State, Integer>{
	
	public List<State> findAllByCountryCountryId(int id);
}
