package com.volunteacher.app.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Donor {

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
	private int donorId;

	@NotNull
	@Size(min = 3 , max = 20)
	@Column(columnDefinition = "Char(30)", length = 20, nullable = false)
	private String donorName;

	@NotNull
	@Column(length = 10, nullable = false)
	private String donorPhone;

	@NotNull
	@Column(length = 40, nullable = false)
	private String donorEmail;

	@NotNull
	@ManyToOne	
	private UserType userType;

	@OneToMany(cascade = CascadeType.ALL , mappedBy = "donor")
	private List<Payment> payment;

	
	public Donor() {
		super();
	}

	public int getDonorId() {
		return donorId;
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

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "Donor [donorId=" + donorId + ", donorName=" + donorName + ", donorPhone=" + donorPhone + ", donorEmail="
				+ donorEmail + ", userType=" + userType + ", payment=" + payment + "]";
	}

	
}
