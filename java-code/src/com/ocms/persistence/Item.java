package com.ocms.persistence;

import java.util.Set;


public class Item {

	private Integer itemId;
	private String name;
	private Double percentage;
	//private Set<CheckitemStatus> checkitemStatuses;
	private Item parent;
	private Set<Item> children;

	public Integer getItemId() {
		return itemId;
	}

	public String getName() {
		return name;
	}

	public Double getPercentage() {
		return percentage;
	}



	public Item getParent() {
		return parent;
	}

	public Set<Item> getChildren() {
		return children;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}


	public void setParent(Item parent) {
		this.parent = parent;
	}

	public void setChildren(Set<Item> children) {
		this.children = children;
	}

}
