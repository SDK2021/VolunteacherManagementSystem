package com.volunteacher.app.service.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.volunteacher.app.exception.ResourceNotFoundException;
import com.volunteacher.app.model.Donor;
import com.volunteacher.app.model.Payment;
import com.volunteacher.app.repository.DonorRepository;
import com.volunteacher.app.repository.PaymentRepository;
import com.volunteacher.app.service.interfaces.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	DonorRepository donorRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Override
	public ResponseEntity<Object> addDonor(Donor donor) 
	{
		try {
			Donor saveDonor = donorRepository.save(donor);
			return ResponseEntity.status(HttpStatus.CREATED).body(saveDonor);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on Donor Creation");
		}
	}

	@Override
	public ResponseEntity<Object> donorList(int id) 
	{
		try {
			Pageable pageable = PageRequest.of(id, 5);
			Page<Donor> donorList = (Page<Donor>) donorRepository.findAll(pageable);
			return ResponseEntity.status(HttpStatus.OK).body(donorList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Donors");
		}
	}

	@Override
	public ResponseEntity<Object> donorById(int id) 
	{
		try {
			Donor donor = donorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Donor is not found for id: "+id));
			return ResponseEntity.status(HttpStatus.OK).body(donor);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on Donor Fetch for id:" +id);
		}
	}

	@Override
	public ResponseEntity<Object> deleteDonor(int id) 
	{
		donorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Donor is not found for id: "+id));
		
		try {
			donorRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Donor is deleted for id: "+id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on Deleting Donors");
		}
	}

	@Override
	public ResponseEntity<Object> addPayment(Payment payment) 
	{
		try {
			Payment savePayment = paymentRepository.save(payment);
			return ResponseEntity.status(HttpStatus.CREATED).body(savePayment);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on Payment Creation");
		}
	}

	@Override
	public ResponseEntity<Object> paymentList(int id) 
	{
		try {
			Pageable pageable = PageRequest.of(id, 5);
			List<Payment> paymentList = (List<Payment>) paymentRepository.findAll();
			return ResponseEntity.status(HttpStatus.OK).body(paymentList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on fetch Payments");
		}
	}

	@Override
	public ResponseEntity<Object> paymentByDonor(int id) 
	{
		try {
			Payment payment = (Payment) paymentRepository.findByDonorDonorId(id);
			
			if(payment == null)
				 throw new ResourceNotFoundException("Donor is not found for id: "+id);
			
			return ResponseEntity.status(HttpStatus.OK).body(payment);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on Payment Fetch for id:" +id);
		}
	}

	@Override
	public ResponseEntity<Object> deletePayment(int id) 
	{
		paymentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Payment is not found for id: "+id));
		
		try {
			paymentRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Payment is deleted for id: "+id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on Deleting payment");
		}
	}

}
