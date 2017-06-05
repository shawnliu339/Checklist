package com.sic.ocms.dto;

import java.util.LinkedHashSet;

import com.sic.ocms.persistence.Item;

public class DashboardDO {
	//id name percentage
	private String parentname;
	private LinkedHashSet<Item> children;

	public DashboardDO(){
		parentname = "";
		children = null;
	}


	public String getParentname() {
		return parentname;
	}
	public LinkedHashSet<Item> getChildren() {
		return children;
	}
	public void setParentname(String parentname) {
		this.parentname = parentname;
	}
	public void setChildren(LinkedHashSet<Item> children) {
		this.children = children;
	}





}
