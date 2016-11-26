package com.ocms.persistance;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Item {
	@Id
	@GeneratedValue
	private int itemId;
	private String name;
	private double percentage;
	private Set<CheckitemStatus> checkitemStatuses;
	private Item parent;
	private Set<Item> children;




	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public Set<CheckitemStatus> getCheckitemStatuses() {
		return checkitemStatuses;
	}
	public void setCheckitemStatuses(Set<CheckitemStatus> checkitemStatuses) {
		this.checkitemStatuses = checkitemStatuses;
	}
	public Item getParent() {
		return parent;
	}
	public void setParent(Item parent) {
		this.parent = parent;
	}
	public Set<Item> getChildren() {
		return children;
	}
	public void setChildren(Set<Item> children) {
		this.children = children;
	}



}
