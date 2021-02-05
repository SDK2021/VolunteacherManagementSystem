package com.sdk;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Donor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 5)
	private int donorId;

	@Column(length = 20, nullable = false)
	private String donorName;

	@Column(length = 10, nullable = false, unique = true)
	private int donorPhone;

	@Column(length = 40, nullable = false, unique = true)
	private String donorEmail;

	@OneToOne
	private UserType usertype;

	public Donor() {
		super();

	}

	public Donor(int donorId, String donorName, int donorPhone, String donorEmail, UserType usertype) {
		super();
		this.donorId = donorId;
		this.donorName = donorName;
		this.donorPhone = donorPhone;
		this.donorEmail = donorEmail;
		this.usertype = usertype;
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

	public int getDonorPhone() {
		return donorPhone;
	}

	public void setDonorPhone(int donorPhone) {
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

	@Override
	public String toString() {
		return "Donor [donorId=" + donorId + ", donorName=" + donorName + ", donorPhone=" + donorPhone + ", donorEmail="
				+ donorEmail + ", usertype=" + usertype + "]";
	}

}
