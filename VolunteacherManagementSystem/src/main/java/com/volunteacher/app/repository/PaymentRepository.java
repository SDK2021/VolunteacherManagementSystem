package com.volunteacher.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Payment;

@Repository
public interface PaymentRepository extends PagingAndSortingRepository<Payment, Integer>{

}
