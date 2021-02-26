package com.volunteacher.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Country;

@Repository
public interface CountryRepository extends PagingAndSortingRepository<Country, Integer> {

}
