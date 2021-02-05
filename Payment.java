package com.sdk;

import java.sql.Date;
import java.sql.Time;
import javax.persistence.Entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="payment_id",length=5)
	private int paymentId;
	
	@Column(nullable=false)
	private Date paymentDate;
	
	@Column(nullable=false)
	private Time paymentTime;
	
	@Column(nullable=false)
	private String paymentMode;
	
	@Column(nullable=false,length=10)
	private int amount;
	
	@Column(nullable=false,length=25)
	private String transactionId;
	
	@OneToOne
	private Donor donor; 
}
