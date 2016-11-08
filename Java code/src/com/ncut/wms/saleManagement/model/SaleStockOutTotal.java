package com.ncut.wms.saleManagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sale_stockout_total")
public class SaleStockOutTotal {
	
	private String sotId;
	private String stId;
	private String createDate;
	private String sendDate;
	private Integer userId;
	private String remark;

	@Id
	public String getSotId() {
		return sotId;
	}

	public void setSotId(String sotId) {
		this.sotId = sotId;
	}

	public String getStId() {
		return stId;
	}

	public void setStId(String stId) {
		this.stId = stId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
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
