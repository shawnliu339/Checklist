package com.ncut.wms.stockManagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "break_total")
public class BreakTotal {

	private String btId;
	private String ritId;
	private String createDate;
	private String returnedDate;
	private Integer stockState = 0;
	private Integer userId;
	private String remark;

	@Id
	public String getBtId() {
		return btId;
	}

	public void setBtId(String btId) {
		this.btId = btId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(String returnedDate) {
		this.returnedDate = returnedDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRitId() {
		return ritId;
	}

	public void setRitId(String ritId) {
		this.ritId = ritId;
	}

	public Integer getStockState() {
		return stockState;
	}

	public void setStockState(Integer stockState) {
		this.stockState = stockState;
	}

}
