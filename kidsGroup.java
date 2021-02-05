package com.sdk;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class kidsGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 5)
	private int groupId;

	@Column(length = 20, nullable = false)
	private String groupName;

	@Column(length = 255, nullable = false)
	private String criteria;

	public kidsGroup() {
		super();

	}

	public kidsGroup(int groupId, String groupName, String criteria) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.criteria = criteria;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
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
		return "kidsGroup [groupId=" + groupId + ", groupName=" + groupName + ", criteria=" + criteria + "]";
	}

}
