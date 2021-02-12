package com.volunteachers.database.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.volunteachers.database.model.Donor;

public interface DonorRepository extends PagingAndSortingRepository<Donor, Integer>{

}
