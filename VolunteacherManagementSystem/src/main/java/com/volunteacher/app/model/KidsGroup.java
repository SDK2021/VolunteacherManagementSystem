package com.volunteacher.app.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class KidsGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(length = 2)
	private int groupId;

	@NotNull
	@Column(length = 20, nullable = false , columnDefinition = "Char")
	private String groupName;

	@NotNull
	@Column(length = 20, nullable = false)
	private String criteria;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "groups")
	private List<Attendance> attendances;

	public KidsGroup() {
		super();
	}

	public KidsGroup(String groupName, String criteria) {
		super();
		this.groupName = groupName;
		this.criteria = criteria;
	}
	
	public int getGroupId() {
		return groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}
	
	
	public List<Attendance> getAttendances() {
		return attendances;
	}

	public void setAttendances(List<Attendance> attendances) {
		this.attendances = attendances;
	}

	@Override
	public String toString() {
		return "KidsGroup [groupId=" + groupId + ", groupName=" + groupName + ", criteria=" + criteria
				+ ", attendances=" + attendances + "]";
	}
	
}
