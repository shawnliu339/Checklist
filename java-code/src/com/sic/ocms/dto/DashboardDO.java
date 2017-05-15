package com.sic.ocms.dto;

import java.util.List;

import com.sic.ocms.persistence.Item;

public class DashboardDO {
	private List<Item> group1;
	private List<Item> group2;
	private List<Item> group3;

	public List<Item> getGroup1() {
		return group1;
	}
	public List<Item> getGroup2() {
		return group2;
	}
	public List<Item> getGroup3() {
		return group3;
	}
	public void setGroup1(List<Item> group1) {
		this.group1 = group1;
	}
	public void setGroup2(List<Item> group2) {
		this.group2 = group2;
	}
	public void setGroup3(List<Item> group3) {
		this.group3 = group3;
	}

}
