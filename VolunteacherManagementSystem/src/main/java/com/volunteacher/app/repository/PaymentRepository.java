package com.volunteachers.database.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.volunteachers.database.model.Payment;

public interface PaymentRepository extends PagingAndSortingRepository<Payment, Integer>{

}
