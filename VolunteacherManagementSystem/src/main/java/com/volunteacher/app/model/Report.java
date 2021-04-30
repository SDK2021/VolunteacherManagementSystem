package com.volunteacher.app.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
@EntityListeners(AuditingEntityListener.class)
public class Report {

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
	private int reportId;
	
	@NotNull
	@Column(nullable = false, length = 50, columnDefinition = "Char(50)")
	private String title;
	
	@NotNull
	@Column(nullable = false, columnDefinition = "Text")
	private String description;
	
	@NotNull
	@Column(nullable = false, columnDefinition = "TEXT")
	private String pdfUrl;
	
	@NotNull
	@CreatedDate
	@JsonFormat(pattern = "MM-dd-yyyy")
	@Column(nullable = false)
	private Calendar creationDate;
	
	@NotNull
	@OneToOne
	@CreatedBy
	private User createdBy;

	
	public Report() {
		super();
	}

	public int getReportId() {
		return reportId;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}
	
	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public String getPdfUrl() {
		return pdfUrl;
	}

	public void setPdfUrl(String pdfUrl) {
		this.pdfUrl = pdfUrl;
	}
	
	
}
