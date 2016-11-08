package com.ncut.wms.stockManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "break_detail")
public class BreakDetail {

	private Integer bdId;
	private String btId;
	private Integer ridId;
	private Integer commodityId;
	private Integer returnedAmount;

	@Id
	@GeneratedValue
	public Integer getBdId() {
		return bdId;
	}

	public void setBdId(Integer bdId) {
		this.bdId = bdId;
	}

	public String getBtId() {
		return btId;
	}

	public void setBtId(String btId) {
		this.btId = btId;
	}

	public Integer getRidId() {
		return ridId;
	}

	public void setRidId(Integer ridId) {
		this.ridId = ridId;
	}

	public Integer getReturnedAmount() {
		return returnedAmount;
	}

	public void setReturnedAmount(Integer returnedAmount) {
		this.returnedAmount = returnedAmount;
	}

	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

}
