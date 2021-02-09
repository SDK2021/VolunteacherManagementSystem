package com.volunteacher.app.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 5)
	private int paymentId;

	@NotNull
	//for storing time at first time--upadateOp???
	@CreatedDate
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date paymentDate;

	@NotNull
	//Search to store time at first time
	@Column(nullable = false)
	@Temporal(TemporalType.TIME)
	private Date paymentTime;

	@NotNull
	@Column(nullable = false, length = 20)
	private String paymentMode;

	@NotNull
	//Check in the table , type should be number of the database column
	@Column(nullable = false, length = 10)
	private double amount;

	@NotNull
	@Column(nullable = false, length = 25)
	private String transactionId;

	@NotNull
	@OneToOne
	private Donor donor;

	public Payment() {
		super();
		
	}

	public Payment(@NotNull Date paymentDate, @NotNull Date paymentTime, @NotNull String paymentMode,
			@NotNull double amount, @NotNull String transactionId, @NotNull Donor donor) {
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

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Date getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
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
