package com.ncut.wms.returned.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "purchase_returned_total")
public class PurchaseReturnedTotal {

	private String prtId;
	private String inStockId;
	private String createDate;
	private Double returnedPrice = 0.0;
	private Integer stockState = 0;
	private Integer userId;
	private String remark;

	@Id
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

	public Double getReturnedPrice() {
		return returnedPrice;
	}

	public void setReturnedPrice(Double returnedPrice) {
		this.returnedPrice = returnedPrice;
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

	public String getInStockId() {
		return inStockId;
	}

	public void setInStockId(String inStockId) {
		this.inStockId = inStockId;
	}

	public Integer getStockState() {
		return stockState;
	}

	public void setStockState(Integer stockState) {
		this.stockState = stockState;
	}

}
