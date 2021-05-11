package com.volunteacher.app.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Payment;

@Repository
public interface PaymentRepository extends PagingAndSortingRepository<Payment, Integer>{
	public List<Payment> findByDonorDonorId(int id);
} 
