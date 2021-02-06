package com.sdk;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class KidsGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 5)
	private int groupId;

	@NotNull
	@Column(length = 20, nullable = false)
	private String groupName;

	@NotNull
	@Column(length = 20, nullable = false)
	private String criteria;

	public KidsGroup() {
		super();

	}

	public KidsGroup(int groupId, String groupName, String criteria) {
		super();
		this.groupId = groupId;
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
		return "kidsGroup [groupId=" + groupId + ", groupName=" + groupName + ", criteria=" + criteria + "]";
	}

}
