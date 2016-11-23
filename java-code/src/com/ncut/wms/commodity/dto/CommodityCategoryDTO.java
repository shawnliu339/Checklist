package com.ncut.wms.commodity.dto;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

public class CommodityCategoryDTO {

	private int cid;
	private String cname;
	private int pid;
	private String pname;

	/* =====datagrid属性====== */
	private int page;
	private int rows;

	public int getCid() {
		return cid;
	}

	public void setCid(int id) {
		this.cid = id;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int parentid) {
		this.pid = parentid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

}
