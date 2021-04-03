package com.volunteacher.app.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class KidsReport {
	
	@Id
	@GeneratedValue(
	    strategy= GenerationType.AUTO,
	    generator="native"
	)
	@GenericGenerator(
	    name = "native",
	    strategy = "native"
	)
	@Column(length = 6)
	private int kidreportId;
	
	@NotNull
	@OneToOne
	private Kid kid;
	
	@NotNull
	@CreatedDate
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(nullable = false, columnDefinition = "Date")
	private Calendar createdDate;
	
	@NotNull
	@CreatedBy
	@ManyToOne
	private User createdBy;
	
	@NotNull
	@Column(nullable = false, length = 8)
	private int discipline;
	
	@NotNull
	@Column(nullable = false, length = 8)
	private int prayer;
	
	@NotNull
	@Column(nullable = false, length = 8)
	private int goshthi;
	
	@NotNull
	@Column(nullable = false, length = 8)
	private int abhivyakti;
	
	@NotNull
	@Column(nullable = false, length = 3)
	private int volunteaching;
	
	@NotNull
	@Column(nullable = false, length = 3)
	private int games;
	
	@NotNull
	@Column(nullable = false, columnDefinition = "Text")
	private String interesArea;
	
	@NotNull
	@Column(nullable =  false, length = 3)
	private int artCraft;
	
	@NotNull
	@Column(nullable =  false, length = 3)
	private int sports;
	
	@NotNull
	@Column(nullable =  false, length = 3)
	private int literature;
	
	@NotNull
	@Column(nullable =  false, length = 3)
	private int attendance;
	
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


	public KidsReport(int kidreportId, Kid kid, Calendar createdDate, User createdBy,
			int discipline,  int prayer, int goshthi, int abhivyakti,
			int volunteaching,  int games,  String interesArea, int artCraft,
			int sports, int literature,  int attendance, String remarks, int maths,
			int gujarati,  int english) {
		super();
		this.kidreportId = kidreportId;
		this.kid = kid;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.discipline = discipline;
		this.prayer = prayer;
		this.goshthi = goshthi;
		this.abhivyakti = abhivyakti;
		this.volunteaching = volunteaching;
		this.games = games;
		this.interesArea = interesArea;
		this.artCraft = artCraft;
		this.sports = sports;
		this.literature = literature;
		this.attendance = attendance;
		this.remarks = remarks;
		this.maths = maths;
		this.gujarati = gujarati;
		this.english = english;
	}


	public int getKidreportId() {
		return kidreportId;
	}


	public void setKidreportId(int kidreportId) {
		this.kidreportId = kidreportId;
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


	public int getDiscipline() {
		return discipline;
	}


	public void setDiscipline(int discipline) {
		this.discipline = discipline;
	}


	public int getPrayer() {
		return prayer;
	}


	public void setPrayer(int prayer) {
		this.prayer = prayer;
	}


	public int getGoshthi() {
		return goshthi;
	}


	public void setGoshthi(int goshthi) {
		this.goshthi = goshthi;
	}


	public int getAbhivyakti() {
		return abhivyakti;
	}


	public void setAbhivyakti(int abhivyakti) {
		this.abhivyakti = abhivyakti;
	}


	public int getVolunteaching() {
		return volunteaching;
	}


	public void setVolunteaching(int volunteaching) {
		this.volunteaching = volunteaching;
	}


	public int getGames() {
		return games;
	}


	public void setGames(int games) {
		this.games = games;
	}


	public String getInteresArea() {
		return interesArea;
	}


	public void setInteresArea(String interesArea) {
		this.interesArea = interesArea;
	}


	public int getArtCraft() {
		return artCraft;
	}


	public void setArtCraft(int artCraft) {
		this.artCraft = artCraft;
	}


	public int getSports() {
		return sports;
	}


	public void setSports(int sports) {
		this.sports = sports;
	}


	public int getLiterature() {
		return literature;
	}


	public void setLiterature(int literature) {
		this.literature = literature;
	}


	public int getAttendance() {
		return attendance;
	}


	public void setAttendance(int attendance) {
		this.attendance = attendance;
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
		return "KidsReport [kidreportId=" + kidreportId + ", kid=" + kid + ", createdDate=" + createdDate
				+ ", createdBy=" + createdBy + ", discipline=" + discipline + ", prayer=" + prayer + ", goshthi="
				+ goshthi + ", abhivyakti=" + abhivyakti + ", volunteaching=" + volunteaching + ", games=" + games
				+ ", interesArea=" + interesArea + ", artCraft=" + artCraft + ", sports=" + sports + ", literature="
				+ literature + ", attendance=" + attendance + ", remarks=" + remarks + ", maths=" + maths
				+ ", gujarati=" + gujarati + ", english=" + english + "]";
	}

}

