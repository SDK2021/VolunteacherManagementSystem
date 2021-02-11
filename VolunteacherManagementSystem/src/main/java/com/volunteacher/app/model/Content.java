package com.volunteacher.app.model;

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
	@Column(nullable = false)
	private String contentData;

	@NotNull
	@OneToOne
	private KidsGroup group;
	

	public Content() {
		super();
	}

	public Content(@NotNull String contentData, @NotNull KidsGroup group) {
		super();
		this.contentData = contentData;
		this.group = group;
	}

	public int getContentId() {
		return contentId;
	}

	public String getContentData() {
		return contentData;
	}

	public void setContentData(String contentData) {
		this.contentData = contentData;
	}

	public KidsGroup getGroup() {
		return group;
	}

	public void setGroup(KidsGroup group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "Content [contentId=" + contentId + ", contentData=" + contentData + ", group=" + group + "]";
	}
}
