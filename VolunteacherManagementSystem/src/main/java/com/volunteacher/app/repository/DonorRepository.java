package com.volunteacher.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Donor;

@Repository
public interface DonorRepository extends PagingAndSortingRepository<Donor, Integer>{

}
