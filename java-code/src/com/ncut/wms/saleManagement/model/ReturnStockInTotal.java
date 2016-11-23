package com.ncut.wms.saleManagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "return_stockin_total")
public class ReturnStockInTotal {

	private String ritId;
	private String srtId;
	private String createDate;
	private String receivedDate;
	private Integer userId;
	private String remark;

	@Id
	public String getRitId() {
		return ritId;
	}

	public void setRitId(String ritId) {
		this.ritId = ritId;
	}

	public String getSrtId() {
		return srtId;
	}

	public void setSrtId(String srtId) {
		this.srtId = srtId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
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

}
