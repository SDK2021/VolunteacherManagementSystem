package com.sdk;

import java.sql.Date;
import java.sql.Time;
import javax.persistence.Entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="payment_id",length=5)
	private int paymentId;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date paymentDate;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIME)
	private Time paymentTime;
	
	@Column(nullable=false)
	private String paymentMode;
	
	@Column(nullable=false,length=10)
	private int amount;
	
	@Column(nullable=false,length=25)
	private String transactionId;
	
	@OneToOne
	private Donor donor;

	public Payment() {
		super();
		
	}

	public Payment(int paymentId, Date paymentDate, Time paymentTime, String paymentMode, int amount,
			String transactionId, Donor donor) {
		super();
		this.paymentId = paymentId;
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

	public Time getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Time paymentTime) {
		this.paymentTime = paymentTime;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
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
