package com.volunteachers.database.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.volunteachers.database.model.Country;

public interface CountryRepository extends PagingAndSortingRepository<Country, Integer> {

}
