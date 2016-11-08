package com.ncut.wms.saleManagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sale_return_total")
public class SaleReturnTotal {

	private String srtId;
	private String stId;
	private String createDate;
	private String returnedDate;
	private String receivedDate;
	private Double returnedPrice;
	private Integer stockState = 0;
	private Integer userId;
	private String remark;

	@Id
	public String getSrtId() {
		return srtId;
	}

	public void setSrtId(String srtId) {
		this.srtId = srtId;
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

	public String getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(String returnedDate) {
		this.returnedDate = returnedDate;
	}

	public Double getReturnedPrice() {
		return returnedPrice;
	}

	public void setReturnedPrice(Double returnedPrice) {
		this.returnedPrice = returnedPrice;
	}

	public Integer getStockState() {
		return stockState;
	}

	public void setStockState(Integer stockState) {
		this.stockState = stockState;
	}

}
