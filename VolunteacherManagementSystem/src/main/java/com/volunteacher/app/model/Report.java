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
public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 6)
	private int reportId;
	
	@NotNull
	@Column(nullable = false, length = 50, columnDefinition = "Char")
	private String title;
	
	@NotNull
	@Column(nullable = false, columnDefinition = "Text")
	private String description;
	
	@NotNull
	@CreatedDate
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Calendar creationDate;
	
	@NotNull
	@OneToOne
	@CreatedBy
	private User craetedBy;

	
	public Report() {
		super();
	}

	public Report( String title, String description, Calendar creationDate,
			 User craetedBy) {
		super();
		this.title = title;
		this.description = description;
		this.creationDate = creationDate;
		this.craetedBy = craetedBy;
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

	public User getCraetedBy() {
		return craetedBy;
	}

	public void setCraetedBy(User craetedBy) {
		this.craetedBy = craetedBy;
	}

	@Override
	public String toString() {
		return "Report [id=" + reportId + ", title=" + title + ", description=" + description + ", creationDate="
				+ creationDate + ", craetedBy=" + craetedBy + "]";
	}
	
	
	
}
