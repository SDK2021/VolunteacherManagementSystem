package com.volunteacher.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import com.sun.istack.NotNull;

@Entity
@Table(name = "KIDS_REPORT")
public class KidsReport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "kidrepoId" , length = 6)
	private int id;
	
	@OneToOne
	private Kid kid;
	
	@NotNull
	@CreatedDate
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date createdDate;
	
	@NotNull
	@CreatedBy
	@OneToOne
	private User createdBy;
	
	@NotNull
	@Column(nullable = false , length = 8)
	private String discipline;
	
	@NotNull
	@Column(nullable = false , length = 8)
	private String prayer;
	
	@NotNull
	@Column(nullable = false , length = 8)
	private String goshthi;
	
	@NotNull
	@Column(nullable = false , length = 8)
	private String abhivyakti;
	
	@NotNull
	@Column(nullable = false , length = 8)
	String volunteaching;
	
	@NotNull
	@Column(nullable = false , length = 8)
	private String nationConnection;
	
	@NotNull
	@Column(nullable = false , length = 8)
	private String games;
	
	@NotNull
	@Column(nullable = false , columnDefinition = "Text")
	private String futureExpectation;
	
	@Column(columnDefinition = "Text")
	private String remarks;
	
	//Change column name
	@NotNull
	@Column(nullable = false , length = 3)
	private int subject1Marks;
	
	@NotNull
	@Column(nullable = false , length = 3)
	private int subject2Marks;
	
	@NotNull
	@Column(nullable = false , length = 3)
	private int subject3Marks;
}
