package com.sic.ocms.persistence;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "checkitem")
public class Checkitem {

	private Integer checkitemId;
	private String content;
	private Integer relatedItemId;
	private Integer relatedGoalId;
	private String Descrition;
	private String typicalDeliverables;
	private Set<CheckitemStatus> checkitemstatus;
//	private Item item;

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

	@Column(name = "description")
	public String getDescrition() {
		return Descrition;
	}

	@Column(name = "typical_deliverables")
	public String getTypicalDeliverables() {
		return typicalDeliverables;
	}

	@OneToMany(mappedBy = "checkitem")
	public Set<CheckitemStatus> getCheckitemstatus() {
		return checkitemstatus;
	}
/*
	@ManyToOne
	@Column(name = "item_id")
	public Item getItem() {
		return item;
	}
*/
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
	public void setDescrition(String descrition) {
		Descrition = descrition;
	}

	public void setTypicalDeliverables(String typicalDeliverables) {
		this.typicalDeliverables = typicalDeliverables;
	}
	public void setCheckitemstatus(Set<CheckitemStatus> checkitemstatus) {
		this.checkitemstatus = checkitemstatus;
	}
/*
	public void setItem(Item item) {
		this.item = item;
	}
*/
}
