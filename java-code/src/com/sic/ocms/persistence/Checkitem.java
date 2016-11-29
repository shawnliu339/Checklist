package com.sic.ocms.persistence;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "checkitem")
public class Checkitem {

	private Integer checkitemId;
	private String content;
	private Integer relatedItemId;
	private Integer relatedGoalId;
	private Set<CheckitemStatus> checkitemstatus;

	@Id
	@GeneratedValue
	@Column(name = "checkitem_id")
	public Integer getCheckitemId() {
		return checkitemId;
	}

	@Column(name = "content")
	public String getContent() {
		return content;
	}

	@Column(name = "related_item_id")
	public Integer getRelatedItemId() {
		return relatedItemId;
	}

	@Column(name = "related_goal_id")
	public Integer getRelatedGoalId() {
		return relatedGoalId;
	}

	@OneToMany(mappedBy = "checkitem")
	public Set<CheckitemStatus> getCheckitemstatus() {
		return checkitemstatus;
	}

	public void setCheckitemId(Integer checkitemId) {
		this.checkitemId = checkitemId;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setRelatedItemId(Integer relatedItemId) {
		this.relatedItemId = relatedItemId;
	}

	public void setRelatedGoalId(Integer relatedGoalId) {
		this.relatedGoalId = relatedGoalId;
	}

	public void setCheckitemstatus(Set<CheckitemStatus> checkitemstatus) {
		this.checkitemstatus = checkitemstatus;
	}

}
