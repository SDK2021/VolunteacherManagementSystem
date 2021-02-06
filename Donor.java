package com.sdk;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Donor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 5)
	private int donorId;

	@NotNull
	@Column(length = 20, nullable = false)
	private String donorName;

	@NotNull
	@Column(length = 10, nullable = false, unique = true)
	private String donorPhone;

	@NotNull
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
