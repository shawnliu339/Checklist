package com.ncut.wms.purchaseManagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="return_stockout_total")
public class ReturnStockOutTotal {

	private String rotId;
	private String prtId;
	private String createDate;
	private Integer userId;
	private String remark;

	@Id
	public String getRotId() {
		return rotId;
	}

	public void setRotId(String rotId) {
		this.rotId = rotId;
	}

	public String getPrtId() {
		return prtId;
	}

	public void setPrtId(String prtId) {
		this.prtId = prtId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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
