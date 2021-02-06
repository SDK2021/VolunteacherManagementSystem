package com.sdk;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Content {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 5)
	private int contentId;

	@NotNull
	@Column(length = 255, nullable = false)
	private String contentData;

	@OneToOne
	private kidsGroup group;

	public Content() {
		super();

	}

	public Content(int contentId, String contentData, kidsGroup group) {
		super();
		this.contentId = contentId;
		this.contentData = contentData;
		this.group = group;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public String getContentData() {
		return contentData;
	}

	public void setContentData(String contentData) {
		this.contentData = contentData;
	}

	public kidsGroup getGroup() {
		return group;
	}

	public void setGroup(kidsGroup group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "Content [contentId=" + contentId + ", contentData=" + contentData + ", group=" + group + "]";
	}
}
