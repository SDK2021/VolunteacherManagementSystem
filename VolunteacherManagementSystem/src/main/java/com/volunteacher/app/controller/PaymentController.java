package com.volunteacher.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.volunteacher.app.model.Donor;
import com.volunteacher.app.model.Payment;
import com.volunteacher.app.service.interfaces.PaymentService;

@RestController
@RequestMapping(path = "/vms")
@CrossOrigin(origins="http://localhost:4200")
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	@GetMapping("/donors")
	public ResponseEntity<Object> getDonorList()
	{
		return paymentService.donorList();
	}
	
	@PostMapping("/donors")
	public ResponseEntity<Object> addDonor(@RequestBody Donor donor)
	{
		return paymentService.addDonor(donor);
	}
	
	@GetMapping("/donors/{id}")
	public ResponseEntity<Object> getDonor(@PathVariable int id)
	{
		return paymentService.donorById(id);
	}
	
	@DeleteMapping("/donors/{id}")
	public ResponseEntity<Object> deleteDonor(@PathVariable int id)
	{
		return paymentService.deleteDonor(id);
	}
	
	@GetMapping("/payments")
	public ResponseEntity<Object> getPaymentsList()
	{
		return paymentService.paymentList();
	}
	
	@PostMapping("/payments")
	public ResponseEntity<Object> addPayment(@RequestBody Payment payment)
	{
		return paymentService.addPayment(payment);
	}
	
	@GetMapping("/payments/{id}")
	public ResponseEntity<Object> getPaymentByDonor(@PathVariable int id)
	{
		return paymentService.paymentByDonor(id);
	}
	
	@DeleteMapping("/payments/{id}")
	public ResponseEntity<Object> deletePayment(@PathVariable int id)
	{
		return paymentService.deletePayment(id);
	}
}
