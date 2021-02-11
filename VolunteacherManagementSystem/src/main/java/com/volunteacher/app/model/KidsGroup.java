package com.volunteacher.app.model;

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
	@Column(length = 2)
	private int groupId;

	@NotNull
	@Column(length = 20, nullable = false , columnDefinition = "Char")
	private String groupName;

	@NotNull
	@Column(length = 20, nullable = false)
	private String criteria;
	

	public KidsGroup() {
		super();
	}

	public KidsGroup(@NotNull String groupName, @NotNull String criteria) {
		super();
		this.groupName = groupName;
		this.criteria = criteria;
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
		return "KidsGroup [groupId=" + groupId + ", groupName=" + groupName + ", criteria=" + criteria + "]";
	}
}
