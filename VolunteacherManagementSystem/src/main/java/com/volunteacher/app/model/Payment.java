package com.volunteacher.app.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Payment {

	@Id
	@GeneratedValue(
	    strategy= GenerationType.AUTO,
	    generator="native"
	)
	@GenericGenerator(
	    name = "native",
	    strategy = "native"
	)
	@Column(length = 5)
	private int paymentId;

	@NotNull
	//for storing time at first time--upadateOp???
	@CreatedDate
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(nullable = false, columnDefinition = "DATE")
	private Calendar paymentDate;

	@NotNull
	//Search to store time at first time
	@Column(nullable = false, columnDefinition = "TIME")
	@JsonFormat(timezone = "IST",pattern = "HH-mm-ss")
	@CreationTimestamp
	private Calendar paymentTime;

	@NotNull
	@Column(nullable = false , length = 20)
	private String paymentMode;

	@NotNull
	//Check in the table , type should be number of the database column
	@Column(nullable = false, length = 10)
	private double amount;

	@NotNull
	@Column(nullable = false, length = 25)
	private String transactionId;

	@NotNull
	@ManyToOne
	private Donor donor;
	

	public Payment() {
		super();
	}

	public Payment(Calendar paymentDate,  Calendar paymentTime,String paymentMode,
			double amount, String transactionId, Donor donor) {
		super();
		this.paymentDate = paymentDate;
		this.paymentTime = paymentTime;
		this.paymentMode = paymentMode;
		this.amount = amount;
		this.transactionId = transactionId;
		this.donor = donor;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public Calendar getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Calendar paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Calendar getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Calendar paymentTime) {
		this.paymentTime = paymentTime;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Donor getDonor() {
		return donor;
	}

	public void setDonor(Donor donor) {
		this.donor = donor;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", paymentDate=" + paymentDate + ", paymentTime=" + paymentTime
				+ ", paymentMode=" + paymentMode + ", amount=" + amount + ", transactionId=" + transactionId
				+ ", donor=" + donor + "]";
	}
}
