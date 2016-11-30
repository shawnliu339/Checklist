package com.sic.ocms.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item {

	private Integer itemId;
	private String name;
	private Double percentage;
	private Checklist checklist;
//	private Item parent;
//	private Set<Item> children;
//	private CheckitemStatus checkitemstatus;



	@Id
	@GeneratedValue
	@Column(name = "item_id")
	public Integer getItemId() {
		return itemId;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	@Column(name = "percentage")
	public Double getPercentage() {
		return percentage;
	}

	@ManyToOne
	@JoinColumn(name="checklist_id")
	public Checklist getChecklist() {
		return checklist;
	}
/*
	@ManyToOne
	@JoinColumn(name = "item_id")
	public Item getParent() {
		return parent;
	}


	@OneToMany(mappedBy = "item")
	public Set<Item> getChildren() {
		return children;
	}

	public CheckitemStatus getCheckitemstatus() {
		return checkitemstatus;
	}
*/
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	public void setChecklist (Checklist checklist) {
		this.checklist = checklist;
	}
/*
	public void setParent(Item parent) {
		this.parent = parent;
	}

	public void setChildren(Set<Item> children) {
		this.children = children;
	}

	public void setCheckitemstatus(CheckitemStatus checkitemstatus) {
		this.checkitemstatus = checkitemstatus;
	}
*/
}
