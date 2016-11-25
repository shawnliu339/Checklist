package com.ocms.persistance;

import java.util.Set;

import javax.persistence.Entity;

@Entity()
public class Checkitem {
	private Integer checkitemId;
	private String content;
	private Integer relatedItemId;
	private Integer relatedGoalId;
	private Set<CheckitemStatus> checkitemstatus;

	public Integer getCheckitemId() {
		return checkitemId;
	}
	public void setCheckitemId(Integer checkitemId) {
		this.checkitemId = checkitemId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getRelatedItemId() {
		return relatedItemId;
	}

	public void setRelatedItemId(Integer relatedItemId) {
		this.relatedItemId = relatedItemId;
	}

	public Integer getRelatedGoalId() {
		return relatedGoalId;
	}

	public void setRelatedGoalId(Integer relatedGoalId) {
		this.relatedGoalId = relatedGoalId;
	}

	public Set<CheckitemStatus> getCheckitemstatus() {
		return checkitemstatus;
	}

	public void setCheckitemstatus(Set<CheckitemStatus> checkitemstatus) {
		this.checkitemstatus = checkitemstatus;
	}

}
