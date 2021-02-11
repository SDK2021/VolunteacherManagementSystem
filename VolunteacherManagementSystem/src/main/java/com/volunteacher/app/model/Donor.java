package com.volunteacher.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Donor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 5)
	private int donorId;

	@NotNull
	@Size(min = 3 , max = 20)
	@Column(columnDefinition = "Char", length = 20, nullable = false)
	private String donorName;

	@NotNull
	@Column(length = 10, nullable = false, unique = true)
	private String donorPhone;

	@NotNull
	@Column(length = 40, nullable = false, unique = true)
	private String donorEmail;

	@NotNull
	@OneToOne
	private UserType userType;
	
	//add
	@OneToOne(cascade = CascadeType.ALL , mappedBy = "donor")
	private Payment payment;

	
	public Donor() {
		super();
	}

	public Donor(@NotNull @Size(min = 3, max = 20) String donorName, @NotNull String donorPhone,
			@NotNull String donorEmail, @NotNull UserType usertype, Payment payment) {
		super();
		this.donorName = donorName;
		this.donorPhone = donorPhone;
		this.donorEmail = donorEmail;
		this.userType = usertype;
		this.payment = payment;
	}

	public int getDonorId() {
		return donorId;
	}

	public void setDonorId(int donorId) {
		this.donorId = donorId;
	}

	public String getDonorName() {
		return donorName;
	}

	public void setDonorName(String donorName) {
		this.donorName = donorName;
	}

	public String getDonorPhone() {
		return donorPhone;
	}

	public void setDonorPhone(String donorPhone) {
		this.donorPhone = donorPhone;
	}

	public String getDonorEmail() {
		return donorEmail;
	}

	public void setDonorEmail(String donorEmail) {
		this.donorEmail = donorEmail;
	}

	public UserType getUsertype() {
		return usertype;
	}

	public void setUsertype(UserType usertype) {
		this.usertype = usertype;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	} 

}
