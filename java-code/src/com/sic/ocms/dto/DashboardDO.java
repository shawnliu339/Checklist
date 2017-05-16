package com.sic.ocms.dto;

import java.util.Set;

import com.sic.ocms.persistence.Item;

public class DashboardDO {
	//id name percentage
	private String parentname;
	private Set<Item> children;

	public DashboardDO(){
		parentname = "";
		children = null;
	}


	public String getParentname() {
		return parentname;
	}
	public Set<Item> getChildren() {
		return children;
	}
	public void setParentname(String parentname) {
		this.parentname = parentname;
	}
	public void setChildren(Set<Item> children) {
		this.children = children;
	}





}
