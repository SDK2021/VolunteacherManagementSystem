package com.volunteacher.app.model;


import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class KidsReport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 6)
	private int kidreportId;
	
	@NotNull
	@OneToOne
	private Kid kid;
	
	@NotNull
	@CreatedDate
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Calendar createdDate;
	
	@NotNull
	@CreatedBy
	@OneToOne
	private User createdBy;
	
	@NotNull
	@Column(nullable = false, length = 8)
	private String discipline;
	
	@NotNull
	@Column(nullable = false, length = 8)
	private String prayer;
	
	@NotNull
	@Column(nullable = false, length = 8)
	private String goshthi;
	
	@NotNull
	@Column(nullable = false, length = 8)
	private String abhivyakti;
	
	@NotNull
	@Column(nullable = false, length = 8)
	String volunteaching;
	
	@NotNull
	@Column(nullable = false, length = 8)
	private String nationConnection;
	
	@NotNull
	@Column(nullable = false, length = 8)
	private String games;
	
	@NotNull
	@Column(nullable = false, columnDefinition = "Text")
	private String futureExpectation;
	
	@Column(columnDefinition = "Text")
	private String remarks;
	
	@NotNull
	@Column(nullable = false, length = 3)
	private int maths;
	
	@NotNull
	@Column(nullable = false, length = 3)
	private int gujarati;
	
	@NotNull
	@Column(nullable = false, length = 3)
	private int english;

	
	public KidsReport() {
		super();
	}

	public KidsReport( Kid kid, Calendar createdDate, User createdBy,
			 String discipline, String prayer, String goshthi, String abhivyakti,
			 String volunteaching, String nationConnection, String games,
			 String futureExpectation, String remarks, int maths, int gujarati,
			 int english) {
		super();
		this.kid = kid;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.discipline = discipline;
		this.prayer = prayer;
		this.goshthi = goshthi;
		this.abhivyakti = abhivyakti;
		this.volunteaching = volunteaching;
		this.nationConnection = nationConnection;
		this.games = games;
		this.futureExpectation = futureExpectation;
		this.remarks = remarks;
		this.maths = maths;
		this.gujarati = gujarati;
		this.english = english;
	}
	
	public int getKidreportId() {
		return kidreportId;
	}

	public Kid getKid() {
		return kid;
	}

	public void setKid(Kid kid) {
		this.kid = kid;
	}

	public Calendar getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Calendar createdDate) {
		this.createdDate = createdDate;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public String getDiscipline() {
		return discipline;
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

	public String getPrayer() {
		return prayer;
	}

	public void setPrayer(String prayer) {
		this.prayer = prayer;
	}

	public String getGoshthi() {
		return goshthi;
	}

	public void setGoshthi(String goshthi) {
		this.goshthi = goshthi;
	}

	public String getAbhivyakti() {
		return abhivyakti;
	}

	public void setAbhivyakti(String abhivyakti) {
		this.abhivyakti = abhivyakti;
	}

	public String getVolunteaching() {
		return volunteaching;
	}

	public void setVolunteaching(String volunteaching) {
		this.volunteaching = volunteaching;
	}

	public String getNationConnection() {
		return nationConnection;
	}

	public void setNationConnection(String nationConnection) {
		this.nationConnection = nationConnection;
	}

	public String getGames() {
		return games;
	}

	public void setGames(String games) {
		this.games = games;
	}

	public String getFutureExpectation() {
		return futureExpectation;
	}

	public void setFutureExpectation(String futureExpectation) {
		this.futureExpectation = futureExpectation;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getMaths() {
		return maths;
	}

	public void setMaths(int maths) {
		this.maths = maths;
	}

	public int getGujarati() {
		return gujarati;
	}


	public void setGujarati(int gujarati) {
		this.gujarati = gujarati;
	}

	public int getEnglish() {
		return english;
	}

	public void setEnglish(int english) {
		this.english = english;
	}

	@Override
	public String toString() {
		return "KidsReport [id=" + kidreportId + ", kid=" + kid + ", createdDate=" + createdDate + ", createdBy=" + createdBy
				+ ", discipline=" + discipline + ", prayer=" + prayer + ", goshthi=" + goshthi + ", abhivyakti="
				+ abhivyakti + ", volunteaching=" + volunteaching + ", nationConnection=" + nationConnection
				+ ", games=" + games + ", futureExpectation=" + futureExpectation + ", remarks=" + remarks + ", maths="
				+ maths + ", gujarati=" + gujarati + ", english=" + english + "]";
	}
}


