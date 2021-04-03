package com.volunteacher.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class KidsGroup {

	@Id
	@GeneratedValue(
	    strategy= GenerationType.AUTO,
	    generator="native"
	)
	@GenericGenerator(
	    name = "native",
	    strategy = "native"
	)
	@Column(length = 2)
	private int groupId;

	@NotNull
	@Column(length = 20, unique = true, nullable = false , columnDefinition = "Char(20)")
	private String groupName;

	@NotNull
	@Column(length = 20, nullable = false)
	private String criteria;
	
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

	@Override
	public String toString() {
		return "KidsGroup [groupId=" + groupId + ", groupName=" + groupName + ", criteria=" + criteria
				+ ", attendances=" + "]";
	}
	
}
