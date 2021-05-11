package com.volunteacher.app.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.volunteacher.app.model.Donor;
import com.volunteacher.app.model.Payment;

public interface PaymentService {
	
	public ResponseEntity<Object> addDonor(Donor donor);
	
	public ResponseEntity<Object> donorList(int id);
	
	public ResponseEntity<Object> donorById(int id);
	
	public ResponseEntity<Object> deleteDonor(int id);
	
	public ResponseEntity<Object> addPayment(Payment payment);
	
	public ResponseEntity<Object> paymentList(int id);
	
	public ResponseEntity<Object> paymentByDonor(int id);
	
	public ResponseEntity<Object> deletePayment(int id);
	
}
