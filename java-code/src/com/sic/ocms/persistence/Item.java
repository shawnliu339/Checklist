package com.sic.ocms.persistence;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item {

	private Integer itemId;
	private String name;
	private Double percentage;
	private Item parent;
	private Set<Item> children = new HashSet<>();
	private Set<Checkitem> checkitems;

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
	@JoinColumn(name = "parent_id")
	public Item getParent() {
		return parent;
	}

	@OneToMany(cascade=CascadeType.ALL, mappedBy = "parent")
	public Set<Item> getChildren() {
		return children;
	}

	@ManyToMany
	@JoinTable(name="checkitem_status",
		joinColumns=@JoinColumn(name="item_id"),
		inverseJoinColumns = @JoinColumn(name = "checkitem_id")
	)
	public Set<Checkitem> getCheckitems() {
		return checkitems;
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

	public void setCheckitems(Set<Checkitem> checkitems) {
		this.checkitems = checkitems;
	}
}
